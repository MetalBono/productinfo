package com.metalbono.service.productinfo.api.category.model

import com.metalbono.service.productinfo.domain.category.model.Category

class UpdateCategoryRequest(
    val id: Long,
    val name: String,
)

fun UpdateCategoryRequest.convertToDomainModel(): Category = Category(
    id = this.id,
    name = this.name,
)