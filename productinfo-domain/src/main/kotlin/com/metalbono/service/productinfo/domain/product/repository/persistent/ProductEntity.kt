package com.metalbono.service.productinfo.domain.product.repository.persistent

import com.metalbono.service.productinfo.domain.product.model.Product
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@Entity
@Table(name = "product", schema = "productinfo")
@SQLRestriction("deleted_at IS NULL")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var price: Long,
    var brandId: Long,
    var categoryId: Long,
    val createdBy: String,
    val createdAt: LocalDateTime,
    var updatedBy: String? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedBy: String? = null,
    var deletedAt: LocalDateTime? = null
) {
    fun convertToDomainModel(): Product = Product(
        id = this.id,
        name = this.name,
        price = this.price,
        brandId = this.brandId,
        categoryId = this.categoryId,
    )

    fun updateBy(
        name: String,
        price: Long,
        brandId: Long,
        categoryId: Long,
        updatedBy: String,
    ): ProductEntity {
        this.name = name
        this.price = price
        this.brandId = brandId
        this.categoryId = categoryId
        this.updatedBy = updatedBy
        this.updatedAt = LocalDateTime.now()
        return this
    }

    fun deleteBy(deletedBy: String): ProductEntity {
        this.deletedBy = deletedBy
        this.deletedAt = LocalDateTime.now()
        return this
    }
}