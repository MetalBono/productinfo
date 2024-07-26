package com.metalbono.service.productinfo.api.brand.model

import com.metalbono.service.productinfo.domain.brand.model.Brand

data class UpdateBrandRequest(
    val id: Long,
    val name: String,
)

fun UpdateBrandRequest.convertToDomainModel(): Brand = Brand(
    id = this.id,
    name = this.name,
)
