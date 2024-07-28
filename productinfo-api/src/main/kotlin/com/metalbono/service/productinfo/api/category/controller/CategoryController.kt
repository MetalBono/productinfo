package com.metalbono.service.productinfo.api.category.controller

import com.metalbono.service.productinfo.api.category.model.toCategoryResponse
import com.metalbono.service.productinfo.domain.category.service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "CategoryController", description = "카테고리 정보 API")
@RestController
@RequestMapping("/api/v1/category")
class CategoryController(
    private val categoryService: CategoryService,
) {
    @Operation(summary = "모든 카테고리 조회", description = "카테고리 전체 목록을 조회한다.")
    @GetMapping("/list/all")
    fun getAllBrands() = categoryService.getAllCategories()
        .map { it.toCategoryResponse() }
}