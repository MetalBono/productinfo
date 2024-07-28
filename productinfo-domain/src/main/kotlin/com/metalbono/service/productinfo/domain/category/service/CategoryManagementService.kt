package com.metalbono.service.productinfo.domain.category.service

import com.metalbono.service.productinfo.domain.category.model.Category
import com.metalbono.service.productinfo.domain.category.model.convertToEntity
import com.metalbono.service.productinfo.domain.category.repository.persistent.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional
class CategoryManagementService(
    private val categoryRepository: CategoryRepository,
) {
    fun addCategory(category: Category): Category {
        return try {
            categoryRepository.save(category.convertToEntity(createdBy = DEFAULT_USER_NAME))
                .convertToDomainModel()
        } catch (e: Exception) {
            throw RuntimeException("카테고리 추가 실패 - message = ${e.message}")
        }
    }

    fun updateCategory(category: Category): Category {
        return category.id?.let {
            categoryRepository.findById(it)
                .map { entity -> entity.updateBy(name = category.name, updatedBy = DEFAULT_USER_NAME) }
                .getOrNull()
        }?.convertToDomainModel()
            ?: throw RuntimeException("카테고리 수정 실패 - 존재하지 않는 카테고리입니다. id = ${category.id}")
    }

    fun deleteCategory(categoryId: Long): Boolean {
        categoryRepository.findById(categoryId)
            .map { entity -> entity.deleteBy(deletedBy = DEFAULT_USER_NAME) }
            .getOrNull() ?: throw RuntimeException("카테고리 삭제 실패 - 존재하지 않는 카테고리입니다. id = $categoryId")
        return true
    }

    companion object {
        const val DEFAULT_USER_NAME = "metalbono"
    }
}