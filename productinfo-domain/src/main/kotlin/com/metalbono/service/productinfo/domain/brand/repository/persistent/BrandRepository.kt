package com.metalbono.service.productinfo.domain.brand.repository.persistent

import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository : JpaRepository<BrandEntity, Long> {
}