/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.kotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = findVersionInt("compileSdk")

        defaultConfig {
            minSdk = findVersionInt("minSdk")
        }

        compileOptions {
            // Up to Java 11 APIs are available through desugaring
            // https://developer.android.com/studio/write/java11-minimal-support-table
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    kotlin()
}

internal fun Project.coreLibraryDesugaring() {
    extensions.findByType(LibraryExtension::class)?.apply {
//        coreLibraryDesugaring(this)
    }
    extensions.findByType(ApplicationExtension::class)?.apply {
//        coreLibraryDesugaring(this)
    }
}

internal fun Project.coreLibraryDesugaring(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileOptions {
            isCoreLibraryDesugaringEnabled = true
        }
    }

    dependencies {
        "coreLibraryDesugaring"(findDependency("desugarJdkLibs"))
    }
}

/**
 * Configure base Kotlin options for JVM (non-Android)
 */
internal fun Project.kotlinJvm() {
    configure<JavaPluginExtension> {
        // Up to Java 11 APIs are available through desugaring
        // https://developer.android.com/studio/write/java11-minimal-support-table
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlin()
}

/**
 * Configure base Kotlin options
 */
private fun Project.kotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // Set JVM target to 11
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            // val warningsAsErrors: String? by project
            // allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs = listOf(
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-Xjsr305=strict"
            )
        }
    }
}
