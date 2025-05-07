plugins {
	kotlin("jvm") version "1.9.21"
	kotlin("plugin.spring") version "1.9.21"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}

val jimmerVersion = "0.9.48"

group = "o_o"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
	google()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
	implementation("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
	ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
	sourceSets.main {
		kotlin.srcDir("build/generated/ksp/main/kotlin")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Javadoc>{
	options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}
