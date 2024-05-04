/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.moshi(includeKspTest: Boolean = false) {
    applyOnce<KspGradleSubplugin>()

    dependencies {
        "ksp"(findDependency("moshiKotlinCodeGen"))
        "implementation"(findDependency("moshi"))
        if (includeKspTest) {
            "kspTest"(findDependency("moshiKotlinCodeGen"))
        }
    }
}
