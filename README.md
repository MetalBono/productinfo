# productinfo
상품과 관련된 데이터 (브랜드, 카테고리, 가격 등)를 다루는 서비스로, <br/>
다음과 같은 멀티모듈로 구성되어있습니다.
```text
productinfo (root project)
├── productinfo-api
│ ├── build.gradle.kts
│ └── src
├── productinfo-domain
│ ├── build.gradle.kts
│ └── src
├── build.gradle.kts
├── setting.gradle.kts
└── README.md
```
* productinfo-api: API 를 제공할 spring web application 모듈
* productinfo-domain: 비즈니스 로직이 구현된 모듈 (domain 모델, JPA Entity 모델 등)
  * 추후 productinfo-batch 등 새 모듈이 추가될 경우 productinfo-domain 을 그대로 활용할 수 있음

## 사전 요구사항
* Java 21
* Gradle 7.x (7.5 or later) and 8.x

## 구현 범위
* Spring Boot / Kotlin / JPA / H2 (in-memory) 를 이용하여 아래의 기능들을 구현했습니다.
  * 구현 1) - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
  * 구현 2) - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    * 조회 대상은 특정 브랜드가 '모든 카테고리' 에 상품이 1개 이상 존재하는 경우에 해당합니다.
    * ex> 브랜드 A가 일부 카테고리에 상품이 없는 경우, 최저가격 판단 대상에서 제외됩니다.
  * 구현 3) - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
    * 카테고리 이름은 완전 일치 검색으로 제공됩니다. (LIKE X)
  * 구현 4) 브랜드 및 카테고리, 상품을 추가 / 업데이트 / 삭제하는 API
    * 하나의 상품은 하나의 카테고리와 하나의 브랜드를 가집니다. (필수)
    * 카테고리는 여러 상품에 매핑될 수 있습니다.
    * 브랜드는 여러 상품에 매핑될 수 있습니다.
* Unit Test 및 Integration Test 는 작성되지 않았습니다.
* Front-end 페이지는 구현되지 않았습니다.
  * 대체제로 기능 테스트를 위한 [swagger-ui](http://localhost:8090/swagger-ui/index.html) 를 제공합니다.

## 어플리케이션 실행 방법
* 서브 모듈 중 productinfo-api 를 실행하면 되며, 방법은 다음과 같습니다.
  * Run Configuration 에 새 Spring Boot Configuration 을 추가합니다.
  * Java version은 21을 설정합니다.
  * 실행할 어플리케이션은 다음을 선택합니다.
    * productinfo.productinfo-api.main
    * com.metalbono.service.productinfo.ProductinfoApiApplication
    * active profiles는 local 을 입력합니다.
* 기본 server.port 는 8090 입니다.

## 기능 테스트 방법
* [swagger-ui](http://localhost:8090/swagger-ui/index.html) 를 통해 API 호출 테스트를 할 수 있습니다.

### 구현 기능별 테스트 방법
* **구현 1) - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API**
  * [카테고리별 최저가 상품 조회 API](http://localhost:8090/swagger-ui/index.html#/ProductInfoController/getMinPriceProductInfoByCategory) 를 실행하여 확인
  * 관련 데이터를 변경하며 테스트하고자 하는 경우 '구현 4)'의 항목들을 이용해주세요.
* **구현 2) - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API**
  * [단일 브랜드의 모든 카테고리 세트 최저가 상품 조회 API](http://localhost:8090/swagger-ui/index.html#/ProductInfoController/getMinPriceInfoBySingleBrandForAllCategory) 를 실행하여 확인
* **구현 3) - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API**
  * [카테고리의 최저가,최고가 상품 조회 API](http://localhost:8090/swagger-ui/index.html#/ProductInfoController/getMinMaxPriceProductInfoByCategoryName) 를 실행하여 확인
  * 카테고리 이름은 완전 일치 검색
* **구현 4) 브랜드 및 카테고리, 상품을 추가 / 업데이트 / 삭제하는 API**
  * 브랜드
    * [브랜드 추가 API](http://localhost:8090/swagger-ui/index.html#/BrandManagementController/addBrand) 를 실행하여 확인
    * [브랜드 수정 API](http://localhost:8090/swagger-ui/index.html#/BrandManagementController/updateBrand) 를 실행하여 확인
    * [브랜드 삭제 API](http://localhost:8090/swagger-ui/index.html#/BrandManagementController/deleteBrand) 를 실행하여 확인
  * 카테고리
    * [카테고리 추가 API](http://localhost:8090/swagger-ui/index.html#/CategoryManagementController/addCategory) 를 실행하여 확인
    * [카테고리 수정 API](http://localhost:8090/swagger-ui/index.html#/CategoryManagementController/updateCategory) 를 실행하여 확인
    * [카테고리 삭제 API](http://localhost:8090/swagger-ui/index.html#/CategoryManagementController/deleteCategory) 를 실행하여 확인
  * 상품
    * [상품 추가 API](http://localhost:8090/swagger-ui/index.html#/ProductManagementController/addProduct) 를 실행하여 확인
    * [상품 수정 API](http://localhost:8090/swagger-ui/index.html#/ProductManagementController/updateProduct) 를 실행하여 확인
    * [상품 삭제 API](http://localhost:8090/swagger-ui/index.html#/ProductManagementController/deleteProduct) 를 실행하여 확인
    * 상품을 추가할 때 사용할 brandId 확인을 위해 아래의 API를 이용하여 모든 브랜드 목록을 조회할 수 있습니다.
      * [모든 브랜드 조회 API](http://localhost:8090/swagger-ui/index.html#/BrandController/getAllBrands)
    * 상품을 추가할 때 사용할 categoryId 확인을 위해 아래의 API를 이용하여 모든 카테고리 목록을 조회할 수 있습니다.
      * [모든 브랜드 조회 API](http://localhost:8090/swagger-ui/index.html#/CategoryController/getAllCategories)

### 기타 사항
* H2 in-memory DB를 사용하고 있으며, [h2-console](http://localhost:8090/h2-console) 에 접속하여 어플리케이션이 실행되는 동안 데이터를 직접 조회할 수 있습니다.
  * 어플리케이션 재기동 시 데이터는 초기화됩니다.
    * 과제 안내문에 기재된 수준의 데이터로 초기화 됩니다. ([data.sql](https://github.com/MetalBono/productinfo/blob/main/productinfo-api/src/main/resources/data.sql) 참고)
* 그 외 구현된 API 항목들은 테스트 등의 용도로 작성되었습니다. 
  * [카테고리의 모든 상품 조회 API](http://localhost:8090/swagger-ui/index.html#/ProductInfoController/getAllProductByCategory)
  * [브랜드의 모든 상품 조회 API](http://localhost:8090/swagger-ui/index.html#/ProductInfoController/getAllProductByBrand)