package com.metalbono.service.productinfo.api.product.controller

import com.metalbono.service.productinfo.api.product.facade.ProductInfoFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product-info")
class ProductInfoController(
    private val productInfoFacade: ProductInfoFacade,
) {
    // 구현 1) - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    @GetMapping("/min-price-products/by-category")
    fun getMinPriceProductInfoByCategory() = productInfoFacade.getMinPriceProductsByCategory()

    // 구현 2) - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    @GetMapping("/min-price/by-single-brand/all-category")
    fun getMinPriceInfoBySingleBrandForAllCategory() = productInfoFacade.getMinPriceInfoBySingleBrandForAllCategory()

    // 구현 3) - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
    @GetMapping("/min-max-price-products/by-category-name")
    fun getMinMaxPriceProductInfoByCategoryName(@RequestParam categoryName: String) =
        productInfoFacade.getMinMaxPriceProductInfoByCategoryName(categoryName)
}