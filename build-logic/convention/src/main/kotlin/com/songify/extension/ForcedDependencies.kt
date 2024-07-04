package com.songify.extension

import org.gradle.api.Project
import org.gradle.api.artifacts.DependencyResolveDetails
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider

fun Project.forcedDependencies() {
    configurations.all {
        resolutionStrategy.eachDependency {
            constrainDependency(libs.okio)
            constrainDependency(libs.moshi)
        }
    }
}

private fun DependencyResolveDetails.constrainDependency(dependencyProvider: Provider<MinimalExternalModuleDependency>) {
    val dependency = dependencyProvider.get()
    if (requested.group == dependency.group && requested.name == dependency.name) {
        useVersion(requireNotNull(dependency.version))
    }
}
