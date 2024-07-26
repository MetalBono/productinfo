package com.metalbono.service.productinfo.api.product.model

import com.metalbono.service.productinfo.domain.product.model.Product

data class UpdateProductRequest(
    val id: Long,
    val name: String,
    val price: Long,
    val brandId: Long,
    val categoryId: Long,
)

fun UpdateProductRequest.convertToDomainModel(): Product = Product(
    id = this.id,
    name = this.name,
    price = this.price,
    brandId = this.brandId,
    categoryId = this.categoryId,
)
