package com.metalbono.service.productinfo.api.product.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "단일 브랜드의 모든 카테고리 세트 최저가 상품 정보 response")
data class MinPriceInfoBySingleBrandForAllCategoryResponse(
    @Schema(description = "최저가 브랜드 정보")
    val minPriceBrandInfo: MinPriceBrandInfoResponse,
)

@Schema(description = "최저가 브랜드 정보 response")
data class MinPriceBrandInfoResponse(
    @Schema(description = "브랜드 ID", example = "1")
    val brandId: Long,
    @Schema(description = "브랜드 이름", example = "A")
    val brandName: String,
    @Schema(description = "상품 목록")
    val products: List<ProductDetailResponse>,
    @Schema(description = "상품 가격 총합", example = "34100")
    val totalPrice: Long,
)