package com.metalbono.service.productinfo.api.product.controller

import com.metalbono.service.productinfo.api.product.model.CreateProductRequest
import com.metalbono.service.productinfo.api.product.model.UpdateProductRequest
import com.metalbono.service.productinfo.api.product.model.convertToDomainModel
import com.metalbono.service.productinfo.api.product.model.toProductResponse
import com.metalbono.service.productinfo.domain.product.service.ProductManagementService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "ProductManagementController", description = "상품 관리 API")
@RestController
@RequestMapping("/api/v1/product-management")
class ProductManagementController(
    private val productManagementService: ProductManagementService,
) {
    @Operation(summary = "상품 추가", description = "새 상품을 추가한다.")
    @PostMapping
    fun addProduct(@RequestBody payload: CreateProductRequest) = productManagementService.addProduct(payload.convertToDomainModel())
        .toProductResponse()

    @Operation(summary = "상품 수정", description = "지정된 상품 정보를 수정한다.")
    @PutMapping
    fun updateProduct(@RequestBody payload: UpdateProductRequest) = productManagementService.updateProduct(payload.convertToDomainModel())
        .toProductResponse()
    @Operation(summary = "상품 삭제", description = "product-id 에 해당하는 상품을 삭제한다.")
    @DeleteMapping("/{product-id}")
    fun deleteProduct(
        @Parameter(description = "상품 ID", example = "1")
        @PathVariable(name = "product-id",
    ) productId: Long) = productManagementService.deleteProduct(productId)
}