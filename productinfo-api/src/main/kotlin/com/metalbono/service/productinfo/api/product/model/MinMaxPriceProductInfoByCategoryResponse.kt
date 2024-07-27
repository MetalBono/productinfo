package com.metalbono.service.productinfo.api.product.model

data class MinMaxPriceProductInfoByCategoryResponse(
    val categoryId: Long,
    val categoryName: String,
    val minPriceProducts: List<ProductInfoResponse>,
    val maxPriceProducts: List<ProductInfoResponse>,
)
