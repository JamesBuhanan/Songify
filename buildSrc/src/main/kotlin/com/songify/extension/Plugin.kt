package com.songify.extension

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer

fun PluginContainer.applyOnce(id: String) {
    if (!hasPlugin(id)) apply(id)
}

inline fun <reified T : Plugin<*>> PluginContainer.applyOnce() {
    if (!hasPlugin(T::class.java)) apply(T::class.java)
}

fun Project.applyOnce(id: String) {
    with(plugins) {
        applyOnce(id)
    }
}

inline fun <reified T : Plugin<*>> Project.applyOnce() {
    with(plugins) {
        applyOnce<T>()
    }
}
