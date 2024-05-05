package com.lemmus.extension

import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.circuit() {
    applyOnce<KspGradleSubplugin>()

    dependencies {
        "implementation"(findBundle("circuit"))
        "implementation"(findDependency("circuitCodegenAnnotations"))

        "ksp"(findDependency("circuitCodegen"))
    }
}
