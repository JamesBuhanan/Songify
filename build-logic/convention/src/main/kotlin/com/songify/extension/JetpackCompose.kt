package com.songify.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradleSubplugin

fun Project.jetpackCompose() {
    applyOnce<ComposeCompilerGradleSubplugin>()

    dependencies {
        "implementation"(libs.bundles.compose)
    }

    android {
        buildFeatures.compose = true
    }
}
