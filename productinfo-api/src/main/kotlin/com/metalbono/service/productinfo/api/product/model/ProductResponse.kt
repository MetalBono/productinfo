package com.metalbono.service.productinfo.api.product.model

import com.metalbono.service.productinfo.domain.product.model.Product
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "상품 정보 response")
data class ProductResponse(
    @Schema(description = "상품 ID", example = "5")
    val id: Long?,
    @Schema(description = "상품 이름", example = "무탠다드 남성 티셔츠")
    val name: String,
    @Schema(description = "브랜드 ID", example = "1")
    val brandId: Long,
    @Schema(description = "카테고리 ID", example = "1")
    val categoryId: Long,
    @Schema(description = "상품 가격", example = "9000")
    val price: Long,
)

fun Product.toProductResponse() = ProductResponse(
    id = this.id,
    name = this.name,
    brandId = this.brandId,
    categoryId = this.categoryId,
    price = this.price,
)