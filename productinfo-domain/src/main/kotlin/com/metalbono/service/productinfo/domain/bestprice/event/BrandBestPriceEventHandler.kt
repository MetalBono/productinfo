package com.metalbono.service.productinfo.domain.bestprice.event

import com.metalbono.service.productinfo.domain.bestprice.event.BrandBestPriceEventType.*
import com.metalbono.service.productinfo.domain.bestprice.repository.persistent.BrandBestPriceEntity
import com.metalbono.service.productinfo.domain.bestprice.repository.persistent.BrandBestPriceRepository
import com.metalbono.service.productinfo.domain.bestprice.repository.persistent.BrandCategoryBestPriceEntity
import com.metalbono.service.productinfo.domain.bestprice.repository.persistent.BrandCategoryBestPriceRepository
import com.metalbono.service.productinfo.domain.category.repository.persistent.CategoryRepository
import com.metalbono.service.productinfo.domain.product.repository.persistent.ProductRepository
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Component
class BrandBestPriceEventHandler(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val brandBestPriceRepository: BrandBestPriceRepository,
    private val brandCategoryBestPriceRepository: BrandCategoryBestPriceRepository,
) {
    @Async
    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleBrandBestPriceUpdateEvent(event: BrandBestPriceUpdateEvent) {
        when (event.eventType) {
            PRODUCT_ADDED, PRODUCT_UPDATED -> updateBrandBestPriceForUpdated(event)
            PRODUCT_DELETED -> updateBrandBestPriceForDeleted(event)
        }
    }

    /**
     * @implNote
     * 상품이 추가되거나 갱신되었을 때
     * brandId + categoryId 의 브랜드 카테고리 별 최저가격 정보가 없거나
     * 저장된 값보다 추가/갱신된 상품의 가격이 싸면 (BrandCategoryBestPriceEntity) - 브랜드 카테고리 별 최저가격 정보를 추가 / 갱신
     * - 정보가 변경됐으면 : 브랜드 별 최저가 정보 재계산해서 갱신 (BrandBestPriceEntity)
     * - 교체되지 않았으면 : 아무것도 안함
     *
     * 단, 최저가 정보에 등록된 상품 ID가 변경되면 무조건 갱신한다.
     */
    fun updateBrandBestPriceForUpdated(event: BrandBestPriceUpdateEvent) {
        val brandCategoryBestPriceEntity = brandCategoryBestPriceRepository.findByBrandIdAndCategoryId(
            brandId = event.brandId,
            categoryId = event.categoryId,
        )
        if (brandCategoryBestPriceEntity == null) {
            brandCategoryBestPriceRepository.save(
                BrandCategoryBestPriceEntity(
                    brandId = event.brandId,
                    categoryId = event.categoryId,
                    price = event.price,
                    productId = event.productId,
                    createdBy = DEFAULT_USER_NAME,
                    createdAt = LocalDateTime.now(),
                )
            )

            updateBrandBestPrice(
                brandId = event.brandId,
                newPrice = event.price,
                oldPrice = 0L,
            )
        } else if (brandCategoryBestPriceEntity.productId == event.productId
            || brandCategoryBestPriceEntity.price > event.price) {
            val oldPrice = brandCategoryBestPriceEntity.price
            brandCategoryBestPriceEntity.updatePriceBy(
                price = event.price,
                productId = event.productId,
                updatedBy = DEFAULT_USER_NAME
            )

            updateBrandBestPrice(
                brandId = event.brandId,
                newPrice = event.price,
                oldPrice = oldPrice,
            )
        }
    }

    /**
     * @implNote
     * 상품이 삭제 되었을 때
     * brandId + categoryId + productId 의 브랜드 카테고리 별 최저가격 정보가 있으면
     * 삭제 후 brand + categoryId 에 대해 브랜드 카테고리 별 최저가격을 새로 조회해서 저장
     * - 브랜드 카테고리 별 최저가격 정보가 삭제되었으면 : 브랜드 별 최저가 정보 재계산해서 갱신 (BrandBestPriceEntity)
     * - 최저가 정보가 없었으면 : 아무것도 안함
     */
    fun updateBrandBestPriceForDeleted(event: BrandBestPriceUpdateEvent) {
        val brandCategoryBestPriceEntity = brandCategoryBestPriceRepository.findByBrandIdAndCategoryIdAndProductId(
            brandId = event.brandId,
            categoryId = event.categoryId,
            productId = event.productId,
        )

        if (brandCategoryBestPriceEntity != null) {
            val oldPrice = brandCategoryBestPriceEntity.price
            brandCategoryBestPriceEntity.deleteBy(DEFAULT_USER_NAME)
            val minPrice = productRepository.findMinPriceProductByCategoryId(event.categoryId)
                .filter { it.brandId == event.brandId }
                .minByOrNull { it.price }
                ?.price ?: 0

            updateBrandBestPrice(
                brandId = event.brandId,
                newPrice = minPrice,
                oldPrice = oldPrice,
            )
        }
    }

    fun updateBrandBestPrice(
        brandId: Long,
        newPrice: Long,
        oldPrice: Long,
     ) {
        // 브랜드 최저가 정보가 있으면 갱신, 없으면 추가
        val brandBestPriceEntity = brandBestPriceRepository.findById(brandId)
            .getOrNull()
            ?.increasePriceBy(
                priceIncreaseAmount = newPrice - oldPrice,
                updatedBy = DEFAULT_USER_NAME,
            ) ?: brandBestPriceRepository.save(
            BrandBestPriceEntity(
                brandId = brandId,
                price = newPrice,
                createdBy = DEFAULT_USER_NAME,
                createdAt = LocalDateTime.now(),
            )
        )

        // 모든 카테고리의 각 카테고리 별 상품 정보가 하나씩 모두 존재해야 유효한 best price 로 설정
        val allCategoryIds = categoryRepository.findAll().map { it.id }
        val brandBestPriceProducts = brandCategoryBestPriceRepository.findByBrandId(brandId)
        val validBestPrice = allCategoryIds.all { id -> brandBestPriceProducts.count { it.categoryId == id } == 1 }
        if (validBestPrice) {
            // 유효한 상태가 되면 best price 항목에 다시 추가
            brandBestPriceEntity.restoreBy(DEFAULT_USER_NAME)
        } else {
            // 유효하지 않은 상태가 되면 best price 항목에서 제거
            brandBestPriceEntity.deleteBy(DEFAULT_USER_NAME)
        }
    }

    companion object {
        const val DEFAULT_USER_NAME = "metalbono"
    }
}