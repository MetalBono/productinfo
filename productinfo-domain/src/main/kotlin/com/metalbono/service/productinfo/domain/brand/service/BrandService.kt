package com.metalbono.service.productinfo.domain.brand.service

import com.metalbono.service.productinfo.domain.brand.repository.persistent.BrandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional(readOnly = true)
class BrandService(
    private val brandRepository: BrandRepository,
) {
    fun getBrandById(brandId: Long) = brandRepository.findById(brandId).getOrNull()
        ?: throw RuntimeException("존재하지 않는 브랜드입니다. brandId - $brandId")

    fun getBrandByIds(brandIds: List<Long>) = brandRepository.findAllById(brandIds)
        .map { it.convertToDomainModel() }
}