package com.metalbono.service.productinfo.domain.product.service

import com.metalbono.service.productinfo.domain.bestprice.event.BrandBestPriceEventType
import com.metalbono.service.productinfo.domain.bestprice.event.BrandBestPriceUpdateEvent
import com.metalbono.service.productinfo.domain.product.model.Product
import com.metalbono.service.productinfo.domain.product.model.convertToEntity
import com.metalbono.service.productinfo.domain.product.repository.persistent.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional
class ProductManagementService(
    private val productRepository: ProductRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {
    fun addProduct(product: Product): Product {
        return try {
            // 상품 정보 저장
            val savedProduct = productRepository.save(product.convertToEntity(createdBy = DEFAULT_USER_NAME))
                .convertToDomainModel()
            // 추가된 정보로 Best price 갱신 요청
            applicationEventPublisher.publishEvent(BrandBestPriceUpdateEvent(
                brandId = savedProduct.brandId,
                categoryId = savedProduct.categoryId,
                productId = savedProduct.id!!,
                price = savedProduct.price,
                eventType = BrandBestPriceEventType.PRODUCT_ADDED,
            ))
            savedProduct
        } catch (e: Exception) {
            throw RuntimeException("상품 추가 실패 - message = ${e.message}")
        }
    }

    fun updateProduct(product: Product): Product {
        // 상품 정보 수정
        val updatedProduct = product.id?.let {
            productRepository.findById(it)
                .map { entity -> entity.updateBy(
                    name = product.name,
                    price = product.price,
                    brandId = product.brandId,
                    categoryId = product.categoryId,
                    updatedBy = DEFAULT_USER_NAME,
                ) }
                .getOrNull()
        }?.convertToDomainModel()
            ?: throw RuntimeException("상품 수정 실패 - 존재하지 않는 상품입니다. id = ${product.id}")

        // 수정된 정보로 Best price 갱신 요청
        applicationEventPublisher.publishEvent(BrandBestPriceUpdateEvent(
            brandId = updatedProduct.brandId,
            categoryId = updatedProduct.categoryId,
            productId = updatedProduct.id!!,
            price = updatedProduct.price,
            eventType = BrandBestPriceEventType.PRODUCT_UPDATED,
        ))
        return updatedProduct
    }

    fun deleteProduct(productId: Long): Boolean {
        // 상품 정보 삭제
        val deletedProduct = productRepository.findById(productId)
            .map { entity -> entity.deleteBy(deletedBy = DEFAULT_USER_NAME) }
            .getOrNull() ?: throw RuntimeException("상품 삭제 실패 - 존재하지 않는 상품입니다. id = $productId")

        // 삭제된 정보로 Best price 갱신 요청
        applicationEventPublisher.publishEvent(BrandBestPriceUpdateEvent(
            brandId = deletedProduct.brandId,
            categoryId = deletedProduct.categoryId,
            productId = deletedProduct.id!!,
            price = deletedProduct.price,
            eventType = BrandBestPriceEventType.PRODUCT_DELETED,
        ))
        return true
    }

    companion object {
        const val DEFAULT_USER_NAME = "metalbono"
    }
}