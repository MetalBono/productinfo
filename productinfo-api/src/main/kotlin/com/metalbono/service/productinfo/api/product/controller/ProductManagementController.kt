package com.metalbono.service.productinfo.api.product.controller

import com.metalbono.service.productinfo.api.product.model.CreateProductRequest
import com.metalbono.service.productinfo.api.product.model.UpdateProductRequest
import com.metalbono.service.productinfo.api.product.model.convertToDomainModel
import com.metalbono.service.productinfo.domain.product.model.Product
import com.metalbono.service.productinfo.domain.product.service.ProductManagementService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product-management")
class ProductManagementController(
    private val productManagementService: ProductManagementService,
) {
    @PostMapping
    fun addProduct(payload: CreateProductRequest): Product {
        return productManagementService.addProduct(payload.convertToDomainModel())
    }

    @PutMapping
    fun updateProduct(payload: UpdateProductRequest): Product {
        return productManagementService.updateProduct(payload.convertToDomainModel())
    }

    @DeleteMapping("/{product-id}")
    fun deleteProduct(@PathVariable(name = "product-id") productId: Long): Boolean {
        return productManagementService.deleteProduct(productId)
    }
}