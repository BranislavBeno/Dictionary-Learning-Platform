import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.gg.jte)
    alias(libs.plugins.git.properties)
    id("java-library-conventions")
    id("spotless-conventions")
}

apply(plugin = "io.spring.dependency-management")

group = "com.dictionary.learning.platform"

val versionMajor = 0
val versionMinor = 1
val versionPatch = 0

version = "v$versionMajor.$versionMinor.$versionPatch"

repositories { mavenCentral() }

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.flyway.core)
    implementation(libs.flyway.database.postgresql)
    implementation(libs.gg.jte.jte)
    implementation(libs.gg.jte.spring.boot)
    runtimeOnly(libs.database.postgresql)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.spring.boot.testcontainers)
    testImplementation(platform(libs.testcontainers.bom))
    testImplementation(libs.testcontainers.common)
    testImplementation(libs.testcontainers.junit.jupiter)
    testImplementation(libs.testcontainers.postgresql)
    testCompileOnly(libs.spring.boot.devtools)
    testRuntimeOnly(libs.junit.platform.launcher)
}

jte {
    generate()
    binaryStaticContent = true
}

springBoot { buildInfo() }

tasks.getByName<BootJar>("bootJar") { this.archiveFileName.set("dictionary-learning-platform.jar") }

tasks.getByName<BootRun>("bootTestRun") { this.jvmArgs = listOf("-Dspring.profiles.active=dev") }
