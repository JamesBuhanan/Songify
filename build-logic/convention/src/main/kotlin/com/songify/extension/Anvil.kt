package com.songify.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin
import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask

fun Project.anvil() {

    applyOnce<Kapt3GradleSubplugin>()
    applyOnce("com.squareup.anvil")

    tasks.withType<KaptGenerateStubsTask>().configureEach {
        // TODO necessary until anvil supports something for K2 contribution merging
        compilerOptions {
            progressiveMode.set(false)
            languageVersion.set(KotlinVersion.KOTLIN_1_9)
        }
    }

    dependencies {
        "implementation"(libs.dagger)
        "kapt"(libs.daggerCompiler)
        "implementation"(project(":library:di:api"))
    }
//        "implementation"(libs.hilt.android)
//        "ksp"(libs.hilt.androidCompiler)
//        if (includeTest) {
//            "kspTest"(libs.hilt.androidCompiler)
//        }
//        if (includeAndroidTest) {
//            "kspAndroidTest"(libs.hilt.androidCompiler)
//        }
//    }
}
