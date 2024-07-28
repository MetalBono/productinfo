package com.metalbono.service.productinfo.api.category.model

import com.metalbono.service.productinfo.domain.category.model.Category
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리 정보 response")
data class CategoryResponse(
    @Schema(description = "카테고리 ID", example = "2")
    val id: Long? = null,
    @Schema(description = "카테고리 이름", example = "스니커즈")
    val name: String,
)

fun Category.toCategoryResponse() = CategoryResponse(
    id = this.id,
    name = this.name,
)