plugins {
    kotlin("android")
}
apply(plugin = "kotlin-kapt")
apply(plugin = "com.google.devtools.ksp")
apply(plugin = "kotlin-parcelize")
apply(plugin = "com.squareup.anvil")

dependencies {
    "implementation"(libs.resolveDependency("timber"))
    "implementation"(libs.resolveDependency("kotlinReflect"))

    // Dagger
    "kapt"(libs.resolveBundle("daggerKapt"))
    "implementation"(libs.resolveBundle("dagger"))

    // Compose
    "implementation"(libs.resolveBundle("compose"))

    // Coroutines
    "implementation"(libs.resolveDependency("kotlin.coroutines.core"))
    "implementation"(libs.resolveDependency("kotlin.coroutines.android"))

    // UnitTest
    "testImplementation"(libs.resolveDependency("junit4"))
    "testImplementation"(libs.resolveDependency("kotlin.coroutines.test"))
    "testImplementation"(libs.resolveDependency("turbine"))
    "testImplementation"(libs.resolveDependency("assertk"))

    // Circuit
    "implementation"(libs.resolveBundle("circuit"))
    "api"(libs.resolveDependency("circuitCodegenAnnotations"))
    "ksp"(libs.resolveDependency("circuitCodegen"))
}
