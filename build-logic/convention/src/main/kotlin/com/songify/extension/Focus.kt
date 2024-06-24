package com.songify.extension

import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply

fun Settings.focus() {
//    if (useProjectIsolation()) {
    apply(from = "settings-all.gradle")
//        apply(from = "../../../../../../settings-all.gradle")
//        return
//    }
//
//    applyOnce<FocusPlugin>()
//
//    configure<FocusExtension> {
//
//    }
}
