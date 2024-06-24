package com.songify.extension

import com.squareup.sort.SortDependenciesPlugin
import org.gradle.api.Project

fun Project.gradleDependenciesSorter() {
    if (isIsolatedProjects()) {
        return
    }

    applyOnce<SortDependenciesPlugin>()

    afterEvaluate {
        val taskCheckSortDependencies = tasks.findByName("checkSortDependencies")
        if (taskCheckSortDependencies != null) {
            rootProject.tasks.findByName("checkSortDependenciesAggregated")!!.apply {
                dependsOn(taskCheckSortDependencies)
            }
        }

        val taskSortDependencies = tasks.findByName("sortDependencies")
        if (taskSortDependencies != null) {
            rootProject.tasks.findByName("sortDependenciesAggregated")!!.apply {
                dependsOn(taskSortDependencies)
            }
        }
    }
}

fun Project.sortDependenciesAggregated() {
    if (isIsolatedProjects()) {
        return
    }

    tasks.register("sortDependenciesAggregated") {
        description = "Executes sortDependencies task for android/jvm libraries"
        dependsOn("sortSettingsGradle")
    }
}

fun Project.checkSortDependenciesAggregated() {
    if (isIsolatedProjects()) {
        return
    }

    tasks.register("checkSortDependenciesAggregated") {
        description = "Executes checkSortDependencies task for android/jvm libraries"
        dependsOn("checkSortSettingsGradle")
    }
}
