package com.songify.extension

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.internal.ParcelizeSubplugin

fun Project.parcelize() {
    applyOnce<ParcelizeSubplugin>()
}
