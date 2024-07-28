package com.metalbono.service.productinfo.domain.product.model

import com.metalbono.service.productinfo.domain.product.repository.persistent.ProductEntity
import java.time.LocalDateTime

data class Product(
    val id: Long? = null,
    val name: String,
    val price: Long,
    val brandId: Long,
    val categoryId: Long,
)

fun Product.convertToEntity(createdBy: String) = ProductEntity(
    id = this.id,
    name = this.name,
    price = this.price,
    brandId = this.brandId,
    categoryId = this.categoryId,
    createdAt = LocalDateTime.now(),
    createdBy = createdBy,
)