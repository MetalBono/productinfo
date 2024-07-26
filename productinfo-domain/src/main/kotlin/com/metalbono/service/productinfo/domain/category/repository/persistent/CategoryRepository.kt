package com.metalbono.service.productinfo.domain.category.repository.persistent

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
}