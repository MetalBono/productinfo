package com.metalbono.service.productinfo.api.product.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리의 최저가, 최고가 상품 정보 response")
data class MinMaxPriceProductInfoByCategoryResponse(
    @Schema(description = "카테고리 ID", example = "1")
    val categoryId: Long,
    @Schema(description = "카테고리 이름", example = "상의")
    val categoryName: String,
    @Schema(description = "최저가 상품 목록")
    val minPriceProducts: List<ProductDetailResponse>,
    @Schema(description = "최고가 상품 목록")
    val maxPriceProducts: List<ProductDetailResponse>,
)
