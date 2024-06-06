package com.songify.extension

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.moduleNameFix() {
    tasks.withType(KotlinCompile::class.java).configureEach {
        // Workaround from https://issuetracker.google.com/issues/284003132#comment12
        compilerOptions {
            moduleName.set(project.path.replace(":", "-"))
        }
    }
}