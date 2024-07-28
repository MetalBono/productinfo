package com.metalbono.service.productinfo.api.category.model

import com.metalbono.service.productinfo.domain.category.model.Category
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리 생성 요청 payload")
data class CreateCategoryRequest(
    @Schema(description = "카테고리 이름", example = "하의")
    val name: String,
)

fun CreateCategoryRequest.convertToDomainModel(): Category = Category(
    name = this.name,
)
