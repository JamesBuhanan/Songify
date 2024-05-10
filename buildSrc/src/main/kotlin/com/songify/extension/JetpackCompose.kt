/*
 * © 2023 Match Group, LLC.
 */
package com.songify.extension

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType

fun Project.jetpackCompose() {
    dependencies {
        "implementation"(libs.bundles.compose)
    }

    val compilerVersion = libs.versions.composeCompiler.get()
    extensions.findByType(LibraryExtension::class)?.apply {
        jetpackComposeCompiler(compilerVersion)
    }
    extensions.findByType(ApplicationExtension::class)?.apply {
        jetpackComposeCompiler(compilerVersion)
    }
}

fun CommonExtension<*, *, *, *, *>.jetpackComposeCompiler(compilerVersion: String) {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = compilerVersion
    }
}
