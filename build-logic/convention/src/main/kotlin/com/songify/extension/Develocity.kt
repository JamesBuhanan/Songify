package com.songify.extension

import com.gradle.develocity.agent.gradle.DevelocityConfiguration
import com.gradle.develocity.agent.gradle.DevelocityPlugin
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.configure

fun Settings.develocity() {
//    if (useProjectIsolation()) {
//        return
//    }

    applyOnce<DevelocityPlugin>()

    configure<DevelocityConfiguration> {
        buildScan {
            publishing.onlyIf { false }
        }
    }
}
