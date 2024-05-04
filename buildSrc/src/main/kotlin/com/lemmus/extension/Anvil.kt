/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.anvil() {
    applyOnce<KspGradleSubplugin>()
    applyOnce("com.squareup.anvil")

    dependencies {
        "implementation"(findDependency("dagger.lib"))
        "implementation"(project(":common:di"))

        "ksp"(findDependency("dagger.compiler"))
    }
}
