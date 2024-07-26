package com.metalbono.service.productinfo.domain.brand.service

import com.metalbono.service.productinfo.domain.brand.model.Brand
import com.metalbono.service.productinfo.domain.brand.model.convertToEntity
import com.metalbono.service.productinfo.domain.brand.repository.persistent.BrandRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class BrandService(
    private val brandRepository: BrandRepository,
) {
    fun addBrand(brand: Brand): Brand {
        return try {
            brandRepository.save(brand.convertToEntity(createdBy = "metalbono"))
                .convertToDomainModel()
        } catch (e: Exception) {
            throw RuntimeException("브랜드 추가 실패 - message = ${e.message}")
        }
    }

    fun updateBrand(brand: Brand): Brand {
        return brand.id?.let {
            brandRepository.findById(it)
                .map { entity -> entity.updateBy(name = brand.name, updatedBy = "metalbono") }
                .getOrNull()
        }?.convertToDomainModel()
            ?: throw RuntimeException("브랜드 수정 실패 - 존재하지 않는 브랜드입니다. id = ${brand.id}")
    }

    fun deleteBrand(brandId: Long): Boolean {
        brandRepository.findById(brandId)
            .map { entity -> entity.deleteBy(deletedBy = "metalbono") }
            .getOrNull() ?: throw RuntimeException("브랜드 삭제 실패 - 존재하지 않는 브랜드입니다. id = $brandId")
        return true
    }
}