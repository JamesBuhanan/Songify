package com.songify.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.test() {
    android {
        testOptions.unitTests.isIncludeAndroidResources = true
    }

    dependencies {
        "testImplementation"(libs.circuitTest)
        "testImplementation"(libs.junit4)
        "testImplementation"(libs.kotlin.coroutines.test)
        "testImplementation"(libs.robolectric)
        "testImplementation"(libs.truth)
    }
}
