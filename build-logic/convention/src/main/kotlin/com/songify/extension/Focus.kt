package com.songify.extension

import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply

fun Settings.focus() {
    apply(from = "settings-all.gradle")
    if (useProjectIsolation()) {
        return
    }

    // applyOnce<FocusPlugin>()
    //
    // configure<FocusExtension> {
    //
    // }
}
