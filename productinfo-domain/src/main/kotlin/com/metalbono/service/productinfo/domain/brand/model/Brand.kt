package com.metalbono.service.productinfo.domain.brand.model

import com.metalbono.service.productinfo.domain.brand.repository.persistent.BrandEntity
import java.time.LocalDateTime

data class Brand(
    val id: Long? = null,
    val name: String,
)

fun Brand.convertToEntity(createdBy: String) = BrandEntity(
    id = this.id,
    name = this.name,
    createdAt = LocalDateTime.now(),
    createdBy = createdBy,
)