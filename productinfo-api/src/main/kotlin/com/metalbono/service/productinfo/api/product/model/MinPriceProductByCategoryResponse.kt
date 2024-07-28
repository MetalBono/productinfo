package com.metalbono.service.productinfo.api.product.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리별 최저가 상품 정보 response")
data class MinPriceProductByCategoryResponse(
    @Schema(description = "상품 가격 총합", example = "36100")
    val totalPrice: Long,
    @Schema(description = "상품 목록")
    val products: List<ProductDetailResponse>,
)