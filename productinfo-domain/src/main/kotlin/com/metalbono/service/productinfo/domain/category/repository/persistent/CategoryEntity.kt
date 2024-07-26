package com.metalbono.service.productinfo.domain.category.repository.persistent

import com.metalbono.service.productinfo.domain.category.model.Category
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@Entity
@Table(name = "category", schema = "productinfo")
@SQLRestriction("deleted_at IS NULL")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
    var updatedBy: String? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedBy: String? = null,
    var deletedAt: LocalDateTime? = null
) {
    fun convertToDomainModel(): Category = Category(
        id = this.id,
        name = this.name
    )

    fun updateBy(name: String, updatedBy: String): CategoryEntity {
        this.name = name
        this.updatedBy = updatedBy
        this.updatedAt = LocalDateTime.now()
        return this
    }

    fun deleteBy(deletedBy: String): CategoryEntity {
        this.deletedBy = deletedBy
        this.deletedAt = LocalDateTime.now()
        return this
    }
}
