package com.songify.extension

import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.detekt() {
    if (isIsolatedProjects()) {
        return
    }

    applyOnce<DetektPlugin>()

    configure<DetektExtension>() {
        baseline = file("detekt-baseline.xml")
        ignoredBuildTypes = listOf("debug", "benchmark")
        parallel = true
        reports {
            html.enabled = true
            xml.enabled = true
            txt.enabled = true
        }
    }

    dependencies {
        "detektPlugins"(libs.detekt.cli)
    }
}
