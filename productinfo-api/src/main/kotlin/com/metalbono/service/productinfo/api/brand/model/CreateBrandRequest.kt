package com.metalbono.service.productinfo.api.brand.model

import com.metalbono.service.productinfo.domain.brand.model.Brand

data class CreateBrandRequest(
    val name: String,
)

fun CreateBrandRequest.convertToDomainModel(): Brand = Brand(
    name = this.name,
)
