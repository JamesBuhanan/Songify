/*
 * © 2023 Match Group, LLC.
 */

package com.songify.extension

import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.moshi() {
    applyOnce<KspGradleSubplugin>()

    dependencies {
        "ksp"(libs.moshiCodeGen)
        "implementation"(libs.moshi)
    }
}
