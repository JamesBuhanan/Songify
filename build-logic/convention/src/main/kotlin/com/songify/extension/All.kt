package com.songify.extension

import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.repositories

fun Project.allProjects() {
    repositories {
        mavenCentral()
        google()
    }

    tasks.withType(JavaCompile::class.java).configureEach {
        options.compilerArgs.add("-Adagger.fastInit=enabled")
    }
}
