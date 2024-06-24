package com.songify.extension

import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

fun Project.allProjects() {
    tasks.withType(JavaCompile::class.java).configureEach {
        options.compilerArgs.add("-Adagger.fastInit=enabled")
    }
}
