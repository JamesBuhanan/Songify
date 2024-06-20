package com.songify.extension

import com.squareup.sort.SortDependenciesPlugin
import java.io.File
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

fun Project.gradleDependenciesSorter() {
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
    tasks.register("sortDependenciesAggregated") {
        description = "Executes sortDependencies task for android/jvm libraries"
        dependsOn("sortSettingsGradle")
    }
}

fun Project.checkSortDependenciesAggregated() {
    tasks.register("checkSortDependenciesAggregated") {
        description = "Executes checkSortDependencies task for android/jvm libraries"
        dependsOn("checkSortSettingsGradle")
    }
}

fun Project.sortSettingsGradle() {
    tasks.register("sortSettingsGradle", SortSettingsTask::class.java) {
        group = "verification"
        description = "Sorts the included projects in settings.gradle"
    }
}

fun Project.checkSortSettingsGradle() {
    tasks.register("checkSortSettingsGradle", CheckSortSettingsGradle::class.java) {
        group = "verification"
        description = "Checks that the included projects in settings.gradle are sorted lexicographically"
    }
}

@CacheableTask
abstract class SortSettingsTask : DefaultTask() {
    @PathSensitive(PathSensitivity.RELATIVE)
    @InputFile
    val settingsGradleInputFile: File = project.rootProject.file("settings.gradle")

    @OutputFile
    val settingsGradleOutputFile: File = project.rootProject.file("settings.gradle")

    @TaskAction
    fun action() {
        val updatedFileLines = mutableListOf<String>()
        val moduleInclusionLines = mutableListOf<String>()

        settingsGradleInputFile.readLines().forEach { fileLine ->
            if (fileLine.startsWith("include", ignoreCase = false)) {
                moduleInclusionLines.add(fileLine)
            } else {
                updatedFileLines.add(fileLine)
            }
        }

        if (settingsGradleOutputFile.exists()) settingsGradleOutputFile.delete()
        settingsGradleOutputFile.createNewFile()

        (updatedFileLines + moduleInclusionLines.apply { sort() }).forEach { fileLine ->
            settingsGradleOutputFile.appendText(fileLine + "\n")
        }
    }
}

@CacheableTask // note: this isn't actually cacheable since there are not declared outputs
abstract class CheckSortSettingsGradle : DefaultTask() {
    @PathSensitive(PathSensitivity.RELATIVE)
    @InputFile
    val settingsGradleInputFile: File = project.rootProject.file("settings.gradle")

    init {
        // There are no outputs, but this task should be up-to-date if the input hasn't changed
        outputs.upToDateWhen { true }
    }

    @TaskAction
    fun action() {
        settingsGradleInputFile.readLines()
            .filter { it.startsWith("include", ignoreCase = false) }
            .zipWithNext { prevLine, nextLine ->
                check(prevLine < nextLine) {
                    "The following lines of settings.gradle are not properly sorted: \"$prevLine\", \"$nextLine\". " +
                        "Please run ./gradlew :sortSettingsGradle to fix."
                }
            }
    }
}
