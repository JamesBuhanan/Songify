/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.moshi() {
    applyOnce<KspGradleSubplugin>()

    dependencies {
        "ksp"(findDependency("moshiCodeGen"))
        "implementation"(findDependency("moshi"))
    }
}
