@file:Suppress("DEPRECATION")

plugins {
    id("com.android.application")
    id("com.lemmus.android")
    id("com.lemmus.compose")
    id("com.lemmus.dependencies")
}

dependencies {
    "implementation"(project(":common:di"))
    "implementation"(libs.resolveBundle("paging"))
}