package com.songify.extension

import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.circuit() {
    applyOnce<KspGradleSubplugin>()
    applyOnce("kotlin-parcelize")

    dependencies {
        "implementation"(libs.bundles.circuit)
        "implementation"(libs.circuitCodegenAnnotations)

        "ksp"(libs.circuitCodegen)
    }
}
