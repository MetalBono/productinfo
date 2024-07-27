package com.metalbono.service.productinfo.domain.category.service

import com.metalbono.service.productinfo.domain.category.model.Category
import com.metalbono.service.productinfo.domain.category.repository.persistent.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryService(
    private val categoryRepository: CategoryRepository,
) {
    fun getCategoryByIds(categoryIds: List<Long>) = categoryRepository.findAllById(categoryIds)
            .map { it.convertToDomainModel() }

    fun getCategoryByName(name: String): Category = categoryRepository.findByName(name)
        ?.convertToDomainModel()
        ?: throw RuntimeException("존재하지 않는 카테고리 입니다. 검색어(이름 - $name)")
}