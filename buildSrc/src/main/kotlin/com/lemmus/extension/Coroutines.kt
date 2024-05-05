package com.lemmus.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.coroutines() {
    dependencies {
        "implementation"(findDependency("kotlin.coroutines.core"))
        "implementation"(findDependency("kotlin.coroutines.android"))
    }
}
