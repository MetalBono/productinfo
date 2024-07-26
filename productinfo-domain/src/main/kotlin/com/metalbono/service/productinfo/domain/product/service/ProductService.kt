package com.metalbono.service.productinfo.domain.product.service

import com.metalbono.service.productinfo.domain.product.model.Product
import com.metalbono.service.productinfo.domain.product.model.convertToEntity
import com.metalbono.service.productinfo.domain.product.repository.persistent.ProductRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun addProduct(product: Product): Product {
        return try {
            productRepository.save(product.convertToEntity(createdBy = "metalbono"))
                .convertToDomainModel()
        } catch (e: Exception) {
            throw RuntimeException("상품 추가 실패 - message = ${e.message}")
        }
    }

    fun updateProduct(product: Product): Product {
        return product.id?.let {
            productRepository.findById(it)
                .map { entity -> entity.updateBy(
                    name = product.name,
                    price = product.price,
                    brandId = product.brandId,
                    categoryId = product.categoryId,
                    updatedBy = "metalbono",
                ) }
                .getOrNull()
        }?.convertToDomainModel()
            ?: throw RuntimeException("상품 수정 실패 - 존재하지 않는 상품입니다. id = ${product.id}")
    }

    fun deleteProduct(productId: Long): Boolean {
        productRepository.findById(productId)
            .map { entity -> entity.deleteBy(deletedBy = "metalbono") }
            .getOrNull() ?: throw RuntimeException("상품 삭제 실패 - 존재하지 않는 상품입니다. id = $productId")
        return true
    }
}