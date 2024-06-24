package com.songify.extension

import com.dropbox.focus.FocusExtension
import com.dropbox.focus.FocusPlugin
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

fun Settings.focus() {
    if (useProjectIsolation()) {
        apply(from = "settings-all.gradle")
        return
    }

    applyOnce<FocusPlugin>()

    configure<FocusExtension> {

    }
}
