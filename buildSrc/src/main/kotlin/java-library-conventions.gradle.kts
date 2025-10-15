import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.jacoco

val libs = the<LibrariesForLibs>()

plugins {
    `java-library`
    jacoco
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

jacoco {
    toolVersion = "0.8.14"
}

dependencies {
    implementation(libs.org.jspecify)
}

tasks.test {
    this.jvmArgs = listOf("-Dspring.profiles.active=dev")
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    afterSuite(
            KotlinClosure2<TestDescriptor, TestResult, Unit>({ descriptor, result ->
                if (descriptor.parent == null) {
                    logger.lifecycle(
                            "\nTest result: ${result.resultType}",
                    )
                    logger.lifecycle(
                            "Test summary: " +
                                    "${result.testCount} tests, " +
                                    "${result.successfulTestCount} succeeded, " +
                                    "${result.failedTestCount} failed, " +
                                    "${result.skippedTestCount} skipped",
                    )
                }
            }),
    )
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}