package com.metalbono.service.productinfo.domain.bestprice.service

import com.metalbono.service.productinfo.domain.bestprice.repository.persistent.BrandBestPriceRepository
import com.metalbono.service.productinfo.domain.bestprice.repository.persistent.BrandCategoryBestPriceRepository
import org.springframework.stereotype.Service

@Service
class BestPriceService(
    private val brandBestPriceRepository: BrandBestPriceRepository,
    private val brandCategoryBestPriceRepository: BrandCategoryBestPriceRepository,
) {
    fun getBestPriceByBrandId() = brandBestPriceRepository.findMinPriceBrand()
        ?.convertToDomainModel()

    fun getBestPriceProducts(brandId: Long) = brandCategoryBestPriceRepository.findByBrandId(brandId)
        .map { it.convertToDomainModel() }
}