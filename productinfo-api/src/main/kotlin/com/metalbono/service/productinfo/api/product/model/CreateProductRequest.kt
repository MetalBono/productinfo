package com.metalbono.service.productinfo.api.product.model

import com.metalbono.service.productinfo.domain.product.model.Product

data class CreateProductRequest(
    val name: String,
    val price: Long,
    val brandId: Long,
    val categoryId: Long,
)

fun CreateProductRequest.convertToDomainModel(): Product = Product(
    name = this.name,
    price = this.price,
    brandId = this.brandId,
    categoryId = this.categoryId,
)
