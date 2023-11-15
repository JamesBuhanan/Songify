@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.lemmus.android")
    id("com.lemmus.dependencies")
}

dependencies {
    "ksp"(libs.resolveDependency("moshiCodeGen"))

    "implementation"(project(":common:coroutines"))
    "implementation"(project(":common:di"))

    // Retrofit
    "implementation"(libs.resolveDependency("retrofit"))
    "implementation"(libs.resolveDependency("retrofit.converter.moshi"))
    "implementation"(libs.resolveDependency("moshi"))
}
