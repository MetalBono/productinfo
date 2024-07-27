package com.metalbono.service.productinfo.api.product.model

data class MinPriceProductByCategoryResponse(
    val totalPrice: Long,
    val products: List<ProductInfoResponse>,
)