package com.songify.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.coroutines() {
    dependencies {
        "implementation"(libs.kotlin.coroutines.core)
        "implementation"(libs.kotlin.coroutines.android)
    }
}
