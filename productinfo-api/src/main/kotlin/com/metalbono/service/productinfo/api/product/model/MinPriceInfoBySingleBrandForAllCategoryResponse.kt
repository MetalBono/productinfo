package com.metalbono.service.productinfo.api.product.model

data class MinPriceInfoBySingleBrandForAllCategoryResponse(
    val minPriceBrandInfo: MinPriceBrandInfoResponse,
)

data class MinPriceBrandInfoResponse(
    val brandId: Long,
    val brandName: String,
    val products: List<ProductInfoResponse>,
    val totalPrice: Long,
)