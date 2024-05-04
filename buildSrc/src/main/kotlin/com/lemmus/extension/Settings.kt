/*
 * © 2024 Match Group, LLC.
 */

package com.lemmus.extension

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
