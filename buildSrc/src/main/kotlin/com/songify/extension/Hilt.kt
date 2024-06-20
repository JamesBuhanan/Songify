package com.songify.extension

import com.google.devtools.ksp.gradle.KspExtension
import com.google.devtools.ksp.gradle.KspGradleSubplugin
import dagger.hilt.android.plugin.HiltGradlePlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.hilt(includeTest: Boolean = false, includeAndroidTest: Boolean = false) {

    applyOnce<KspGradleSubplugin>()
    applyOnce<HiltGradlePlugin>()

    extensions.findByType(KspExtension::class.java)?.apply {
        arg("circuit.codegen.mode", "hilt")
    }

    dependencies {
        "implementation"(libs.hilt.android)
        "ksp"(libs.hilt.androidCompiler)
        if (includeTest) {
            "kspTest"(libs.hilt.androidCompiler)
        }
        if (includeAndroidTest) {
            "kspAndroidTest"(libs.hilt.androidCompiler)
        }
    }
}
