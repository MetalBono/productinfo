package com.metalbono.service.productinfo.domain.product.service

import com.metalbono.service.productinfo.domain.product.repository.persistent.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductInfoService(
    private val productRepository: ProductRepository,
) {
    fun getProductsByIds(productIds: List<Long>) = productRepository.findAllById(productIds)
        .map { it.convertToDomainModel() }

    fun getMinPriceProductsByCategory() = productRepository.findMinPriceProductIdsByCategory()
        .let { productRepository.findAllById(it) }
        .map { it.convertToDomainModel() }

    fun getMinPriceProductsByCategoryId(categoryId: Long) = productRepository.findMinPriceProductByCategoryId(categoryId)
        .map { it.convertToDomainModel() }

    fun getMaxPriceProductsByCategoryId(categoryId: Long) = productRepository.findMaxPriceProductByCategoryId(categoryId)
        .map { it.convertToDomainModel() }

    fun getProductsByBrandId(brandId: Long) = productRepository.findByBrandId(brandId)
        .map { it.convertToDomainModel() }

    fun getProductsByCategoryId(categoryId: Long) = productRepository.findByCategoryId(categoryId)
        .map { it.convertToDomainModel() }
}