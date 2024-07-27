package com.metalbono.service.productinfo.domain.bestprice.repository.persistent

import com.metalbono.service.productinfo.domain.bestprice.model.BrandBestPrice
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@Entity
@Table(name = "brand_best_price", schema = "productinfo")
@SQLRestriction("deleted_at IS NULL")
data class BrandBestPriceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var brandId: Long,
    var price: Long,
    val createdBy: String,
    val createdAt: LocalDateTime,
    var updatedBy: String? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedBy: String? = null,
    var deletedAt: LocalDateTime? = null
) {
    fun convertToDomainModel() = BrandBestPrice(
        brandId = this.brandId,
        price = this.price,
    )

    fun increasePriceBy(
        priceIncreaseAmount: Long,
        updatedBy: String,
    ): BrandBestPriceEntity {
        this.price += priceIncreaseAmount
        this.updatedBy = updatedBy
        this.updatedAt = LocalDateTime.now()
        return this
    }
}