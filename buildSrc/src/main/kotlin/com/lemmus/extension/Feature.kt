package com.lemmus.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.feature() {
    dependencies {
        "implementation"(project(":common:coroutines"))
        "implementation"(project(":common:di"))
        "implementation"(project(":common:resultlistener"))
        "implementation"(project(":common:theme"))
        "implementation"(project(":common:ui"))
    }
}
