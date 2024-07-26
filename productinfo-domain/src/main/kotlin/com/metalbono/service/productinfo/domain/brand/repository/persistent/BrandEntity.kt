package com.metalbono.service.productinfo.domain.brand.repository.persistent

import com.metalbono.service.productinfo.domain.brand.model.Brand
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@Entity
@Table(name = "brand", schema = "productinfo")
@SQLRestriction("deleted_at IS NULL")
data class BrandEntity(
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
    fun convertToDomainModel(): Brand = Brand(
        id = this.id,
        name = this.name
    )

    fun updateBy(name: String, updatedBy: String): BrandEntity {
        this.name = name
        this.updatedBy = updatedBy
        this.updatedAt = LocalDateTime.now()
        return this
    }

    fun deleteBy(deletedBy: String): BrandEntity {
        this.deletedBy = deletedBy
        this.deletedAt = LocalDateTime.now()
        return this
    }
}
