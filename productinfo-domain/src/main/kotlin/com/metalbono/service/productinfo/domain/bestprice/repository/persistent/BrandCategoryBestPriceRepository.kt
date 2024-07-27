package com.metalbono.service.productinfo.domain.bestprice.repository.persistent

import org.springframework.data.jpa.repository.JpaRepository

interface BrandCategoryBestPriceRepository : JpaRepository<BrandCategoryBestPriceEntity, Long> {

    fun findByBrandId(brandId: Long): List<BrandCategoryBestPriceEntity>

    fun findByBrandIdAndCategoryId(brandId: Long, categoryId: Long): BrandCategoryBestPriceEntity?

    fun findByBrandIdAndCategoryIdAndProductId(brandId: Long, categoryId: Long, productId: Long): BrandCategoryBestPriceEntity?
}