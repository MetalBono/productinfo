package com.metalbono.service.productinfo.api.brand.controller

import com.metalbono.service.productinfo.api.brand.model.CreateBrandRequest
import com.metalbono.service.productinfo.api.brand.model.UpdateBrandRequest
import com.metalbono.service.productinfo.api.brand.model.convertToDomainModel
import com.metalbono.service.productinfo.api.brand.model.toBrandResponse
import com.metalbono.service.productinfo.domain.brand.service.BrandManagementService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "BrandManagementController", description = "브랜드 관리 API")
@RestController
@RequestMapping("/api/v1/brand-management")
class BrandManagementController(
    private val brandManagementService: BrandManagementService,
) {
    @Operation(summary = "브랜드 추가", description = "새 브랜드를 추가한다.")
    @PostMapping
    fun addBrand(@RequestBody payload: CreateBrandRequest) = brandManagementService.addBrand(payload.convertToDomainModel())
        .toBrandResponse()

    @Operation(summary = "브랜드 수정", description = "지정된 브랜드 정보를 수정한다.")
    @PutMapping
    fun updateBrand(@RequestBody payload: UpdateBrandRequest) = brandManagementService.updateBrand(payload.convertToDomainModel())
        .toBrandResponse()

    @Operation(summary = "브랜드 삭제", description = "brand-id 에 해당하는 브랜드를 삭제한다.")
    @DeleteMapping("/{brand-id}")
    fun deleteBrand(
        @Parameter(description = "브랜드 ID", example = "1")
        @PathVariable(name = "brand-id") brandId: Long,
    ): Boolean = brandManagementService.deleteBrand(brandId)
}