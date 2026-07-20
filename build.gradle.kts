plugins {
    kotlin("jvm") version "2.4.10"
    id("org.jetbrains.dokka") version "2.2.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.10.2"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug")
    runtimeOnly(platform("org.slf4j:slf4j-bom:2.0.18"))
    runtimeOnly("org.slf4j:slf4j-simple")
    testImplementation(kotlin("test-junit"))
    testImplementation("org.araqnid.kotlin.assert-that:assert-that:0.1.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs = setOf(
                "kotlin.RequiresOptIn",
                "kotlinx.coroutines.ExperimentalCoroutinesApi",
            ).map { "-opt-in=$it" }
        }
    }
}
