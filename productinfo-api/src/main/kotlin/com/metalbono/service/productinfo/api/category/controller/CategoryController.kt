package com.metalbono.service.productinfo.api.category.controller

import com.metalbono.service.productinfo.api.category.model.CreateCategoryRequest
import com.metalbono.service.productinfo.api.category.model.UpdateCategoryRequest
import com.metalbono.service.productinfo.api.category.model.convertToDomainModel
import com.metalbono.service.productinfo.domain.category.model.Category
import com.metalbono.service.productinfo.domain.category.service.CategoryManagementService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/category-management")
class CategoryController(
    private val categoryManagementService: CategoryManagementService,
) {
    @PostMapping
    fun addCategory(payload: CreateCategoryRequest): Category {
        return categoryManagementService.addCategory(payload.convertToDomainModel())
    }

    @PutMapping
    fun updateCategory(payload: UpdateCategoryRequest): Category {
        return categoryManagementService.updateCategory(payload.convertToDomainModel())
    }

    @DeleteMapping("/{category-id}")
    fun deleteCategory(@PathVariable(name = "category-id") categoryId: Long): Boolean {
        return categoryManagementService.deleteCategory(categoryId)
    }
}