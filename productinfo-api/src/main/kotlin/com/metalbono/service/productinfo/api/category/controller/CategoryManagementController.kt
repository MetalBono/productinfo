package com.metalbono.service.productinfo.api.category.controller

import com.metalbono.service.productinfo.api.category.model.CreateCategoryRequest
import com.metalbono.service.productinfo.api.category.model.UpdateCategoryRequest
import com.metalbono.service.productinfo.api.category.model.convertToDomainModel
import com.metalbono.service.productinfo.api.category.model.toCategoryResponse
import com.metalbono.service.productinfo.domain.category.service.CategoryManagementService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "CategoryManagementController", description = "카테고리 관리 API")
@RestController
@RequestMapping("/api/v1/category-management")
class CategoryManagementController(
    private val categoryManagementService: CategoryManagementService,
) {
    @Operation(summary = "카테고리 추가", description = "새 카테고리를 추가한다.")
    @PostMapping
    fun addCategory(@RequestBody payload: CreateCategoryRequest) = categoryManagementService.addCategory(payload.convertToDomainModel())
        .toCategoryResponse()

    @Operation(summary = "카테고리 수정", description = "지정된 카테고리 정보를 수정한다.")
    @PutMapping
    fun updateCategory(@RequestBody payload: UpdateCategoryRequest) = categoryManagementService.updateCategory(payload.convertToDomainModel())
        .toCategoryResponse()

    @Operation(summary = "카테고리 삭제", description = "category-id 에 해당하는 카테고리를 삭제한다.")
    @DeleteMapping("/{category-id}")
    fun deleteCategory(
        @Parameter(description = "카테고리 ID", example = "1")
        @PathVariable(name = "category-id") categoryId: Long,
    ) = categoryManagementService.deleteCategory(categoryId)
}