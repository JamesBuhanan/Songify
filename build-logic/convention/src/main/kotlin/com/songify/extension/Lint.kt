package com.songify.extension

import org.gradle.api.Project

fun Project.configureLint() {
    android {
        lint {
            baseline = file("lint-baseline.xml")
            enable += "ConvertToWebp"
            ignoreWarnings = true
            xmlOutput = file("build/reports/lint/${project.name}-lint-results.xml")
            htmlOutput = file("build/reports/lint/${project.name}-lint-results.html")
        }
    }
}
