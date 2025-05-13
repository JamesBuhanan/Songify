package com.songify.extension

import org.gradle.api.Project

fun Project.addStartScreen() {
    if (project.path == providers.gradleProperty("startScreen").orNull) {
        android {
            sourceSets {
                getByName("debug").apply {
                    java.srcDir("src/startscreen/kotlin")
                }
            }
        }
    }
}
// Provide afterLoginScreen -> include /dev source set // maybe it should be /afterloginscreen. I don't think :feature:*:internal will ever need this directory for anything else?
// real library vs fake? -> Separate modules?