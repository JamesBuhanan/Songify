package com.songify.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.feature() {
    dependencies {
        "implementation"(project(":common:theme"))
        "implementation"(project(":library:coroutines:public"))
        "implementation"(project(":library:loading:public"))
    }
}
