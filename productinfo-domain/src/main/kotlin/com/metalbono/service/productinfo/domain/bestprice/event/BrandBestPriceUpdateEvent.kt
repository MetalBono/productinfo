package com.metalbono.service.productinfo.domain.bestprice.event

data class BrandBestPriceUpdateEvent(
    val brandId: Long,
    val categoryId: Long,
    val productId: Long,
    val price: Long,
    val eventType: BrandBestPriceEventType
)

enum class BrandBestPriceEventType {
    PRODUCT_ADDED,       // 상품 정보가 추가됨
    PRODUCT_UPDATED,     // 상품 정보가 갱신됨
    PRODUCT_DELETED,     // 상품이 삭제됨
    ;
}