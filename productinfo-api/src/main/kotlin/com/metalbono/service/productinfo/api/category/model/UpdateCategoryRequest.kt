package com.metalbono.service.productinfo.api.category.model

import com.metalbono.service.productinfo.domain.category.model.Category
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리 수정 요청 payload")
class UpdateCategoryRequest(
    @Schema(description = "카테고리 ID", example = "2")
    val id: Long,
    @Schema(description = "카테고리 이름", example = "스니커즈")
    val name: String,
)

fun UpdateCategoryRequest.convertToDomainModel(): Category = Category(
    id = this.id,
    name = this.name,
)