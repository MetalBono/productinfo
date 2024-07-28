package com.metalbono.service.productinfo.api.brand.model

import com.metalbono.service.productinfo.domain.brand.model.Brand
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "브랜드 정보 response")
data class BrandResponse(
    @Schema(description = "브랜드 ID", example = "2")
    val id: Long? = null,
    @Schema(description = "브랜드 이름", example = "어디다스")
    val name: String,
)

fun Brand.toBrandResponse() = BrandResponse(
    id = this.id,
    name = this.name,
)
