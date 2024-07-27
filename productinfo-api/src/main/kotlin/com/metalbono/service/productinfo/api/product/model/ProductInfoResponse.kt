package com.metalbono.service.productinfo.api.product.model

import com.metalbono.service.productinfo.domain.brand.model.Brand
import com.metalbono.service.productinfo.domain.category.model.Category
import com.metalbono.service.productinfo.domain.product.model.Product

data class ProductInfoResponse(
    val id: Long?,
    val name: String,
    val brandId: Long,
    val brandName: String,
    val categoryId: Long,
    val categoryName: String,
    val price: Long,
)

fun Product.toProductInfoResponse(brand: Brand?, category: Category?) = ProductInfoResponse(
    id = this.id,
    name = this.name,
    brandId = brand?.id ?: 0,
    brandName = brand?.name ?: "",
    categoryId = category?.id ?: 0,
    categoryName = category?.name ?: "",
    price = this.price,
)
