package com.metalbono.service.productinfo.domain.product.repository.persistent

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, Long> {
}