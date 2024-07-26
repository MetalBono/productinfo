package com.metalbono.service.productinfo.domain.category.model

import com.metalbono.service.productinfo.domain.category.repository.persistent.CategoryEntity
import java.time.LocalDateTime

data class Category(
    val id: Long? = null,
    val name: String,
)

fun Category.convertToEntity(createdBy: String) = CategoryEntity(
    id = this.id,
    name = this.name,
    createdAt = LocalDateTime.now(),
    createdBy = createdBy,
)