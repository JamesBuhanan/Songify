package com.songify.extension

import com.dropbox.affectedmoduledetector.AffectedModuleConfiguration
import com.dropbox.affectedmoduledetector.AffectedModuleDetectorPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.affectedModules() {
    if (isIsolatedProjects()) {
        return
    }

    applyOnce<AffectedModuleDetectorPlugin>()

    configure<AffectedModuleConfiguration> {
        baseDir = "$rootDir"
        pathsAffectingAllModules = setOf(
            "buildSrc/src/main/kotlin/com/songify/extension/AffectedModules.kt",
            "buildSrc/src/main/kotlin/com/songify/extension/Lint.kt",
            "gradle/libs.versions.toml",
            "settings-all.gradle",
        )
        excludedModules = setOf(
            ":app:Twig"
        )
        logFilename = "amd-output.log"
        logFolder = "$rootDir"
        specifiedBranch = "main"
        compareFrom = "SpecifiedBranchCommitMergeBase"
        includeUncommitted = false
        customTasks = setOf(
            AffectedModuleConfiguration.CustomTask(
                "runAffectedDetekt",
                "detekt",
                "Run Detekt",
            )
        )
    }
}
