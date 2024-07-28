package com.metalbono.service.productinfo.api.brand.controller

import com.metalbono.service.productinfo.api.brand.model.toBrandResponse
import com.metalbono.service.productinfo.domain.brand.service.BrandService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "BrandController", description = "브랜드 정보 API")
@RestController
@RequestMapping("/api/v1/brand")
class BrandController(
    private val brandService: BrandService,
) {
    @Operation(summary = "모든 브랜드 조회", description = "브랜브 전체 목록을 조회한다.")
    @GetMapping("/list/all")
    fun getAllBrands() = brandService.getAllBrands()
        .map { it.toBrandResponse() }
}