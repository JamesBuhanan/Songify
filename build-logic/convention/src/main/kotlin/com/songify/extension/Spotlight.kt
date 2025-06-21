package com.songify.extension

import com.fueledbycaffeine.spotlight.SpotlightSettingsPlugin
import com.fueledbycaffeine.spotlight.dsl.SpotlightExtension
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.configure

fun Settings.spotlight() {
    applyOnce<SpotlightSettingsPlugin>()

    configure<SpotlightExtension> {
//        typeSafeAccessorInference.set(TypeSafeAccessorInference.STRICT)
    }
}
