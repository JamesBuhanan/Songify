package com.songify.extension

import app.cash.better.dynamic.features.BetterDynamicFeaturesPlugin
import org.gradle.api.Project

fun Project.betterDynamicFeatures() {
    applyOnce<BetterDynamicFeaturesPlugin>()
}
