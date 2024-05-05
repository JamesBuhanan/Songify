package com.lemmus.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.retrofit() {
    dependencies {
        "implementation"(findDependency("retrofit"))
        "implementation"(findDependency("retrofit.converter.moshi"))
    }
}
