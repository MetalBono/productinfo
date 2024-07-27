package com.metalbono.service.productinfo.api.product.facade

import com.metalbono.service.productinfo.api.product.model.*
import com.metalbono.service.productinfo.domain.bestprice.service.BestPriceService
import com.metalbono.service.productinfo.domain.brand.service.BrandService
import com.metalbono.service.productinfo.domain.category.service.CategoryService
import com.metalbono.service.productinfo.domain.product.service.ProductInfoService
import org.springframework.stereotype.Service

@Service
class ProductInfoFacade(
    private val brandService: BrandService,
    private val categoryService: CategoryService,
    private val productInfoService: ProductInfoService,
    private val bestPriceService: BestPriceService,
) {
    fun getMinPriceProductsByCategory(): MinPriceProductByCategoryResponse = kotlin.runCatching {
        val products = productInfoService.getMinPriceProductsByCategory()
        val brandMap = brandService.getBrandByIds(products.map { it.brandId }).associateBy { it.id }
        val categoryMap = categoryService.getCategoryByIds(products.map { it.categoryId }).associateBy { it.id }
        return MinPriceProductByCategoryResponse(
            totalPrice = products.sumOf { it.price },
            products = products.map { it.toProductInfoResponse(
                brand = brandMap[it.brandId],
                category = categoryMap[it.categoryId],
            )}.sortedBy { it.categoryId })
    }.onFailure { e ->
        throw RuntimeException("카테고리 별 최저가 상품 조회 중 오류 발생. message - ${e.message}")
    }.getOrThrow()

    fun getMinPriceInfoBySingleBrandForAllCategory(): MinPriceInfoBySingleBrandForAllCategoryResponse = kotlin.runCatching {
        // 현재 brand 풀세트 최저가 조회 (brandId, price)
        val brandBestPrice = bestPriceService.getBestPriceByBrandId()
            ?: return MinPriceInfoBySingleBrandForAllCategoryResponse(
                minPriceBrandInfo = MinPriceBrandInfoResponse(
                    brandId = 0,
                    brandName = "",
                    products = emptyList(),
                    totalPrice = 0,
                )
            )

        // brand ID 로 최저가 세트 상품 ID 목록 전체 조회
        val brandId = brandBestPrice.brandId
        val brandBestPriceProducts = bestPriceService.getBestPriceProducts(brandId)

        // brand, category 정보 조회
        val brand = brandService.getBrandById(brandId).convertToDomainModel()
        val categoryMap = categoryService.getCategoryByIds(brandBestPriceProducts.map { it.categoryId }).associateBy { it.id }

        // 상품 ID 목록으로 상품 목록 조회
        val products = productInfoService.getProductsByIds(brandBestPriceProducts.map { it.productId })
            .map { it.toProductInfoResponse(brand, categoryMap[it.categoryId]) }

        return MinPriceInfoBySingleBrandForAllCategoryResponse(
            minPriceBrandInfo = MinPriceBrandInfoResponse(
                brandId = brand.id!!,
                brandName = brand.name,
                products = products,
                totalPrice = brandBestPrice.price,
            )
        )
    }.onFailure { e ->
        throw RuntimeException("단일 브랜드 최저가 정보 조회 중 오류 발생. message - ${e.message}")
    }.getOrThrow()

    fun getMinMaxPriceProductInfoByCategoryName(categoryName: String): MinMaxPriceProductInfoByCategoryResponse = kotlin.runCatching {
        val category = categoryService.getCategoryByName(categoryName)
        val minPriceProducts = productInfoService.getMinPriceProductsByCategoryId(category.id!!)
        val maxPriceProducts = productInfoService.getMaxPriceProductsByCategoryId(category.id!!)
        val brandMap = brandService.getBrandByIds(
            minPriceProducts.map { it.brandId } + maxPriceProducts.map { it.brandId }
        ).associateBy { it.id }
        return MinMaxPriceProductInfoByCategoryResponse(
            categoryId = category.id!!,
            categoryName = category.name,
            minPriceProducts = minPriceProducts.map { it.toProductInfoResponse(brandMap[it.brandId], category) },
            maxPriceProducts = maxPriceProducts.map { it.toProductInfoResponse(brandMap[it.brandId], category) },
        );
    }.onFailure { e ->
        throw RuntimeException("카테고리 이름으로 최저가, 최고가 상품 조회 중 오류 발생. message - ${e.message}")
    }.getOrThrow()
}