package com.metalbono.service.productinfo.api.brand.controller

import com.metalbono.service.productinfo.api.brand.model.CreateBrandRequest
import com.metalbono.service.productinfo.api.brand.model.UpdateBrandRequest
import com.metalbono.service.productinfo.api.brand.model.convertToDomainModel
import com.metalbono.service.productinfo.domain.brand.model.Brand
import com.metalbono.service.productinfo.domain.brand.service.BrandService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/brand")
class BrandController(
    private val brandService: BrandService,
) {
    @PostMapping
    fun addBrand(payload: CreateBrandRequest): Brand {
        return brandService.addBrand(payload.convertToDomainModel())
    }

    @PutMapping
    fun updateBrand(payload: UpdateBrandRequest): Brand {
        return brandService.updateBrand(payload.convertToDomainModel())
    }

    @DeleteMapping("/{brand-id}")
    fun deleteBrand(@PathVariable(name = "brand-id") brandId: Long): Boolean {
        return brandService.deleteBrand(brandId)
    }
}