package com.metalbono.service.productinfo.domain.product.repository.persistent

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<ProductEntity, Long> {

    @Query(
        value = """
        SELECT MAX(p.id)
        FROM ProductEntity p
        JOIN (
            SELECT 
                sub.categoryId AS categoryId,
                MIN(sub.price) AS minPrice
            FROM ProductEntity sub
            GROUP BY sub.categoryId
        ) AS minPriceByCategory
        ON p.categoryId = minPriceByCategory.categoryId AND p.price = minPriceByCategory.minPrice
        GROUP BY p.categoryId
        """,
    )
    fun findMinPriceProductIdsByCategory(): List<Long>

    @Query(
        value = """
        SELECT p
        FROM ProductEntity p
        WHERE p.price = (
            SELECT MIN(sub.price)
            FROM ProductEntity sub
            WHERE sub.categoryId = :categoryId
        )
        AND p.categoryId = :categoryId
    """
    )
    fun findMinPriceProductByCategoryId(categoryId: Long): List<ProductEntity>

    @Query(
        value = """
        SELECT p
        FROM ProductEntity p
        WHERE p.price = (
            SELECT MAX(sub.price)
            FROM ProductEntity sub
            WHERE sub.categoryId = :categoryId
        )
        AND p.categoryId = :categoryId
    """
    )
    fun findMaxPriceProductByCategoryId(categoryId: Long): List<ProductEntity>
}