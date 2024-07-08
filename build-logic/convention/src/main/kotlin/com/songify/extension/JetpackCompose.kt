package com.songify.extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.jetpackCompose() {
    applyOnce("org.jetbrains.kotlin.plugin.compose")

    dependencies {
        "implementation"(libs.bundles.compose)
    }

    val compilerVersion = libs.versions.composeCompiler.get()

    android {
        jetpackComposeCompiler(compilerVersion)
    }
}

fun CommonExtension<*, *, *, *, *, *>.jetpackComposeCompiler(compilerVersion: String) {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = compilerVersion
    }
}
