@file:Suppress("DEPRECATION")

plugins {
    id("com.android.library")
    id("com.lemmus.android")
    id("com.lemmus.compose")
    id("com.lemmus.dependencies")
}

dependencies {
    "implementation"(project(":common:coroutines"))
    "implementation"(project(":common:di"))
    "implementation"(project(":common:resultlistener"))
    "implementation"(project(":common:theme"))
    "implementation"(project(":common:ui"))

    // Image loading
    "implementation"(libs.resolveDependency("coil"))

    // Paging
    "implementation"(libs.resolveBundle("paging"))

    "testImplementation"(libs.resolveDependency("circuitTest"))
    "testImplementation"(libs.resolveDependency("robolectric"))

    "androidTestImplementation"(libs.resolveDependency("androidx.compose.test.junit4"))
    "androidTestImplementation"(libs.resolveDependency("leakcanary.android.instrumentation"))
    "androidTestImplementation"(libs.resolveDependency("truth"))
    "androidTestImplementation"(libs.resolveDependency("androidx.compose.ui.testing.manifest"))
}
