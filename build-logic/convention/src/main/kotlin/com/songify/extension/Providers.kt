package com.songify.extension

import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.api.provider.ProviderFactory

fun Project.getBooleanGradleProperty(property: String, default: Boolean): Boolean {
    return providers.getBooleanGradleProperty(property, default)
}

fun Project.getStringGradleProperty(property: String, default: String): String {
    return providers.gradleProperty(property).map { it }.getOrElse(default)
}

fun Settings.getBooleanGradleProperty(property: String, default: Boolean): Boolean {
    return providers.getBooleanGradleProperty(property, default)
}

fun ProviderFactory.getBooleanGradleProperty(property: String, default: Boolean): Boolean {
    return gradleProperty(property).map { it.toBooleanStrict() }.getOrElse(default)
}

fun ProviderFactory.getStringGradleProperty(property: String, default: String): String {
    return gradleProperty(property).map { it }.getOrElse(default)
}

val ProviderFactory.useProjectIsolation: Boolean
    get() = getBooleanGradleProperty(
        "org.gradle.unsafe.isolated-projects",
        false
    )

fun Project.useProjectIsolation(): Boolean = providers.useProjectIsolation


fun Settings.useProjectIsolation(): Boolean = providers.useProjectIsolation

