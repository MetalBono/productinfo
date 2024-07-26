package com.metalbono.service.productinfo.api.category.model

import com.metalbono.service.productinfo.domain.category.model.Category

data class CreateCategoryRequest(
    val name: String,
)

fun CreateCategoryRequest.convertToDomainModel(): Category = Category(
    name = this.name,
)
