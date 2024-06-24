package com.songify.extension

import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.resolve.RepositoriesMode

fun Settings.dependencyResolutionManagement() {
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
        repositories {
            mavenCentral()
        }
    }
}
