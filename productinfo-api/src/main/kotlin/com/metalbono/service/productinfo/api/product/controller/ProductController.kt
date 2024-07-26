package com.metalbono.service.productinfo.api.product.controller

import com.metalbono.service.productinfo.api.product.model.CreateProductRequest
import com.metalbono.service.productinfo.api.product.model.UpdateProductRequest
import com.metalbono.service.productinfo.api.product.model.convertToDomainModel
import com.metalbono.service.productinfo.domain.product.model.Product
import com.metalbono.service.productinfo.domain.product.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    private val productService: ProductService,
) {
    @PostMapping
    fun addProduct(payload: CreateProductRequest): Product {
        return productService.addProduct(payload.convertToDomainModel())
    }

    @PutMapping
    fun updateProduct(payload: UpdateProductRequest): Product {
        return productService.updateProduct(payload.convertToDomainModel())
    }

    @DeleteMapping("/{product-id}")
    fun deleteProduct(@PathVariable(name = "product-id") productId: Long): Boolean {
        return productService.deleteProduct(productId)
    }
}