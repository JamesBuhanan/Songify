package com.songify.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.androidTest() {
    android {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    dependencies {
        "androidTestImplementation"(libs.androidx.compose.ui.test.manifest)
        "androidTestImplementation"(libs.androidx.compose.ui.test.junit4.android)
        "androidTestImplementation"(libs.circuitTest)
        "androidTestImplementation"(libs.junit4)
    }
}
