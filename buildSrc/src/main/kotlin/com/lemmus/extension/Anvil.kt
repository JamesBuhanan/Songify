/*
 * © 2023 Match Group, LLC.
 */

package com.songify.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin

fun Project.anvil() {
    applyOnce<Kapt3GradleSubplugin>()
    applyOnce("com.squareup.anvil")

    dependencies {
        "implementation"(libs.dagger.lib)
        "implementation"(project(":common:di"))

        "kapt"(libs.dagger.compiler)
    }
}
