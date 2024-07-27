package com.metalbono.service.productinfo.domain.bestprice.repository.persistent

import com.metalbono.service.productinfo.domain.bestprice.model.BrandCategoryBestPrice
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@Entity
@Table(name = "brand_category_best_price", schema = "productinfo")
@SQLRestriction("deleted_at IS NULL")
class BrandCategoryBestPriceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var brandId: Long,
    var categoryId: Long,
    var price: Long,
    var productId: Long,
    val createdBy: String,
    val createdAt: LocalDateTime,
    var updatedBy: String? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedBy: String? = null,
    var deletedAt: LocalDateTime? = null
) {
    fun convertToDomainModel() = BrandCategoryBestPrice(
        brandId = this.brandId,
        categoryId = this.categoryId,
        price = this.price,
        productId = this.productId,
    )

    fun updatePriceBy(
        price: Long,
        productId: Long,
        updatedBy: String,
    ): BrandCategoryBestPriceEntity {
        this.price = price
        this.productId = productId
        this.updatedBy = updatedBy
        this.updatedAt = LocalDateTime.now()
        return this
    }

    fun deleteBy(
        deletedBy: String,
    ): BrandCategoryBestPriceEntity {
        this.deletedBy = deletedBy
        this.deletedAt = LocalDateTime.now()
        return this
    }
}