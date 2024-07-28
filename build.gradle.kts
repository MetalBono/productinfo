plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.jpa") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
}

allprojects {
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "io.spring.dependency-management")

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(21)
		}
	}

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.named<Jar>("jar") {
		enabled = true
	}
}

kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}


