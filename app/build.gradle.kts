plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.gg.jte)
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
    implementation(libs.gg.jte.jte)
    implementation(libs.gg.jte.spring.boot)
    testImplementation(libs.spring.boot.starter.test)
    testRuntimeOnly(libs.junit.platform.launcher)
}

jte {
    generate()
    binaryStaticContent = true
}
