package com.metalbono.service.productinfo.domain.bestprice.repository.persistent

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BrandBestPriceRepository : JpaRepository<BrandBestPriceEntity, Long> {

    @Query(value = """
        SELECT b
        FROM BrandBestPriceEntity b
        WHERE b.price = (
            SELECT MIN(sub.price)
            FROM BrandBestPriceEntity sub
        )
    """)
    fun findMinPriceBrand(): BrandBestPriceEntity?
}