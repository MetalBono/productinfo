plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":productinfo-domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
}