/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin

fun Project.anvil() {
    applyOnce<Kapt3GradleSubplugin>()
    applyOnce("com.squareup.anvil")

    dependencies {
        "implementation"(findDependency("dagger.lib"))
        "implementation"(project(":common:di"))

        "kapt"(findDependency("dagger.compiler"))
    }
}
