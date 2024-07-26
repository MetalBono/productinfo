package com.metalbono.service.productinfo.api.category.controller

import com.metalbono.service.productinfo.api.category.model.CreateCategoryRequest
import com.metalbono.service.productinfo.api.category.model.UpdateCategoryRequest
import com.metalbono.service.productinfo.api.category.model.convertToDomainModel
import com.metalbono.service.productinfo.domain.category.model.Category
import com.metalbono.service.productinfo.domain.category.service.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(
    private val categoryService: CategoryService,
) {
    @PostMapping
    fun addCategory(payload: CreateCategoryRequest): Category {
        return categoryService.addCategory(payload.convertToDomainModel())
    }

    @PutMapping
    fun updateCategory(payload: UpdateCategoryRequest): Category {
        return categoryService.updateCategory(payload.convertToDomainModel())
    }

    @DeleteMapping("/{category-id}")
    fun deleteCategory(@PathVariable(name = "category-id") categoryId: Long): Boolean {
        return categoryService.deleteCategory(categoryId)
    }
}