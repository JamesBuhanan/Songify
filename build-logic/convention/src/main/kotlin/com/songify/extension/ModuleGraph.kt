package com.songify.extension

import dev.iurysouza.modulegraph.gradle.ModuleGraphExtension
import dev.iurysouza.modulegraph.gradle.ModuleGraphPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.moduleGraph() {
    applyOnce<ModuleGraphPlugin>()

    configure<ModuleGraphExtension> {
        readmePath.set("./READMETEST.md")
        heading.set("Module Graph")
    }
}
