package com.metalbono.service.productinfo.api.brand.controller

import com.metalbono.service.productinfo.api.brand.model.CreateBrandRequest
import com.metalbono.service.productinfo.api.brand.model.UpdateBrandRequest
import com.metalbono.service.productinfo.api.brand.model.convertToDomainModel
import com.metalbono.service.productinfo.domain.brand.model.Brand
import com.metalbono.service.productinfo.domain.brand.service.BrandManagementService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/brand-management")
class BrandController(
    private val brandManagementService: BrandManagementService,
) {
    @PostMapping
    fun addBrand(@RequestBody payload: CreateBrandRequest): Brand {
        return brandManagementService.addBrand(payload.convertToDomainModel())
    }

    @PutMapping
    fun updateBrand(@RequestBody payload: UpdateBrandRequest): Brand {
        return brandManagementService.updateBrand(payload.convertToDomainModel())
    }

    @DeleteMapping("/{brand-id}")
    fun deleteBrand(@PathVariable(name = "brand-id") brandId: Long): Boolean {
        return brandManagementService.deleteBrand(brandId)
    }
}