-- 브랜드 초기 데이터 생성
INSERT INTO productinfo.brand (name, created_by, created_at) VALUES
    ('A', 'metalbono', CURRENT_TIMESTAMP),
    ('B', 'metalbono', CURRENT_TIMESTAMP),
    ('C', 'metalbono', CURRENT_TIMESTAMP),
    ('D', 'metalbono', CURRENT_TIMESTAMP),
    ('E', 'metalbono', CURRENT_TIMESTAMP),
    ('F', 'metalbono', CURRENT_TIMESTAMP),
    ('G', 'metalbono', CURRENT_TIMESTAMP),
    ('H', 'metalbono', CURRENT_TIMESTAMP),
    ('I', 'metalbono', CURRENT_TIMESTAMP);

-- 카테고리 초기 데이터 생성
INSERT INTO productinfo.category (name, created_by, created_at)
VALUES
    ('상의', 'metalbono', CURRENT_TIMESTAMP),
    ('아우터', 'metalbono', CURRENT_TIMESTAMP),
    ('바지', 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈', 'metalbono', CURRENT_TIMESTAMP),
    ('가방', 'metalbono', CURRENT_TIMESTAMP),
    ('모자', 'metalbono', CURRENT_TIMESTAMP),
    ('양말', 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리', 'metalbono', CURRENT_TIMESTAMP);

-- 상품 초기 데이터 생성
INSERT INTO productinfo.product (name, price, brand_id, category_id, created_by, created_at)
VALUES
    ('상의_A', 11200, 1, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_A', 5500, 1, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_A', 4200, 1, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_A', 9000, 1, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_A', 2000, 1, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_A', 1700, 1, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_A', 1800, 1, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_A', 2300, 1, 8, 'metalbono', CURRENT_TIMESTAMP),

    ('상의_B', 10500, 2, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_B', 5900, 2, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_B', 3800, 2, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_B', 9100, 2, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_B', 2100, 2, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_B', 2000, 2, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_B', 2000, 2, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_B', 2200, 2, 8, 'metalbono', CURRENT_TIMESTAMP),

    ('상의_C', 10000, 3, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_C', 6200, 3, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_C', 3300, 3, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_C', 9200, 3, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_C', 2200, 3, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_C', 1900, 3, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_C', 2200, 3, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_C', 2100, 3, 8, 'metalbono', CURRENT_TIMESTAMP),

    ('상의_D', 10100, 4, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_D', 5100, 4, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_D', 3000, 4, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_D', 9500, 4, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_D', 2500, 4, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_D', 1500, 4, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_D', 2400, 4, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_D', 2000, 4, 8, 'metalbono', CURRENT_TIMESTAMP),

    ('상의_E', 10700, 5, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_E', 5000, 5, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_E', 3800, 5, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_E', 9900, 5, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_E', 2300, 5, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_E', 1800, 5, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_E', 2100, 5, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_E', 2100, 5, 8, 'metalbono', CURRENT_TIMESTAMP),

    ('상의_F', 11200, 6, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_F', 7200, 6, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_F', 4000, 6, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_F', 9300, 6, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_F', 2100, 6, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_F', 1600, 6, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_F', 2300, 6, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_F', 1900, 6, 8, 'metalbono', CURRENT_TIMESTAMP),

    ('상의_G', 10500, 7, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_G', 5800, 7, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_G', 3900, 7, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_G', 9000, 7, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_G', 2200, 7, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_G', 1700, 7, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_G', 2100, 7, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_G', 2000, 7, 8, 'metalbono', CURRENT_TIMESTAMP),

    ('상의_H', 10800, 8, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_H', 6300, 8, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_H', 3100, 8, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_H', 9700, 8, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_H', 2100, 8, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_H', 1600, 8, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_H', 2000, 8, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_H', 2000, 8, 8, 'metalbono', CURRENT_TIMESTAMP),

    ('상의_I', 11400, 9, 1, 'metalbono', CURRENT_TIMESTAMP),
    ('아우터_I', 6700, 9, 2, 'metalbono', CURRENT_TIMESTAMP),
    ('바지_I', 3200, 9, 3, 'metalbono', CURRENT_TIMESTAMP),
    ('스니커즈_I', 9500, 9, 4, 'metalbono', CURRENT_TIMESTAMP),
    ('가방_I', 2400, 9, 5, 'metalbono', CURRENT_TIMESTAMP),
    ('모자_I', 1700, 9, 6, 'metalbono', CURRENT_TIMESTAMP),
    ('양말_I', 1700, 9, 7, 'metalbono', CURRENT_TIMESTAMP),
    ('액세서리_I', 2400, 9, 8, 'metalbono', CURRENT_TIMESTAMP);

-- 상품 초기 데이터에 기반한 브랜드 별 best price 정보 생성
INSERT INTO productinfo.brand_best_price (brand_id, price, created_by, created_at)
VALUES
     (1, 37700, 'metalbono', CURRENT_TIMESTAMP),
     (2, 37600, 'metalbono', CURRENT_TIMESTAMP),
     (3, 37100, 'metalbono', CURRENT_TIMESTAMP),
     (4, 36100, 'metalbono', CURRENT_TIMESTAMP),
     (5, 37700, 'metalbono', CURRENT_TIMESTAMP),
     (6, 39600, 'metalbono', CURRENT_TIMESTAMP),
     (7, 37200, 'metalbono', CURRENT_TIMESTAMP),
     (8, 37600, 'metalbono', CURRENT_TIMESTAMP),
     (9, 39000, 'metalbono', CURRENT_TIMESTAMP);

-- 상품 초기 데이터에 기반한 브랜드 + 카테고리 항목 별 best price 정보 생성
INSERT INTO productinfo.brand_category_best_price (brand_id, category_id, price, product_id, created_by, created_at)
SELECT brand_id, category_id, MIN(price), id, 'metalbono', CURRENT_TIMESTAMP
FROM productinfo.product
GROUP BY brand_id, category_id;