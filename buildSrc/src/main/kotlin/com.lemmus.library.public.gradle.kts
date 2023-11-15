@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.lemmus.android")
}

dependencies {
    "ksp"(libs.resolveDependency("moshiCodeGen"))
    "implementation"(libs.resolveDependency("moshi"))
}
