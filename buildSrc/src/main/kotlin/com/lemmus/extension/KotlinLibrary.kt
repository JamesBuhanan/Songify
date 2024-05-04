/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

fun Project.kotlinLibrary() {
    tasks.withType<Test>().configureEach {
        systemProperty("kotlinx.coroutines.stacktrace.recovery", "false")
    }
}
