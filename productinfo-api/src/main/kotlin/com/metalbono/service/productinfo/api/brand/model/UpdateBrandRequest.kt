package com.metalbono.service.productinfo.api.brand.model

import com.metalbono.service.productinfo.domain.brand.model.Brand
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "브랜드 수정 요청 payload")
data class UpdateBrandRequest(
    @Schema(description = "브랜드 ID", example = "2")
    val id: Long,
    @Schema(description = "브랜드 이름", example = "어디다스")
    val name: String,
)

fun UpdateBrandRequest.convertToDomainModel(): Brand = Brand(
    id = this.id,
    name = this.name,
)
