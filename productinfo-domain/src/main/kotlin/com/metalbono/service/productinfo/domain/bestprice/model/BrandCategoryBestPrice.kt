package com.metalbono.service.productinfo.domain.bestprice.model

data class BrandCategoryBestPrice(
    val brandId: Long,
    val categoryId: Long,
    val price: Long,
    val productId: Long,
)