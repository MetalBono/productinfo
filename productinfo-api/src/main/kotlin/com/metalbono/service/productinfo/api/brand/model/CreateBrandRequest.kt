package com.metalbono.service.productinfo.api.brand.model

import com.metalbono.service.productinfo.domain.brand.model.Brand
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "브랜드 생성 요청 payload")
data class CreateBrandRequest(
    @Schema(description = "브랜드 이름", example = "어디다스")
    val name: String,
)

fun CreateBrandRequest.convertToDomainModel(): Brand = Brand(
    name = this.name,
)
