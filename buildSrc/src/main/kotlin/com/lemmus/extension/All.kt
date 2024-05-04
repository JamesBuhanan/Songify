/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.buildscript
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

fun Project.allProjects() {
    buildscript {
        repositories {
            mavenCentral()
        }
    }

    tasks.withType(JavaCompile::class.java).configureEach {
        options.compilerArgs.add("-Adagger.fastInit=enabled")
    }

    afterEvaluate {
        extensions.findByType(KaptExtension::class.java)?.apply {
            arguments {
                arg("dagger.fastInit", "enabled")
            }
        }
    }
}
