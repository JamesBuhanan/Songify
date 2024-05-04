/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import java.util.Optional
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

/**
 * Wraps VersionCatalog#findBundle to give a clean error message in the event of a failure
 */
fun VersionCatalog.resolveBundle(bundle: String): Provider<ExternalModuleDependencyBundle>? =
    resolveCatalogItem(VersionCatalog::findBundle, bundle)

/**
 * Wraps VersionCatalog#findDependency to give a clean error message in the event of a failure
 */
fun VersionCatalog.resolveDependency(dependency: String): Provider<MinimalExternalModuleDependency>? =
    resolveCatalogItem(VersionCatalog::findLibrary, dependency)

/**
 * Wraps VersionCatalog#findVersion to give a clean error message in the event of a failure
 */
fun VersionCatalog.resolveVersion(version: String): String? =
    resolveCatalogItem(VersionCatalog::findVersion, version)?.requiredVersion

fun VersionCatalog.resolveVersionInt(version: String): Int? = resolveVersion(version)?.toInt()

private fun <T> VersionCatalog.resolveCatalogItem(
    finder: VersionCatalog.(String) -> Optional<T>,
    identifier: String,
): T? {
    return try {
        finder(identifier).get()
    } catch (ex: NoSuchElementException) {
        null
    }
}

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.buildLibs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("buildLibs")

fun Project.findVersion(version: String): String {
    return libs.resolveVersion(version) ?: buildLibs.resolveVersion(version) ?: throw NullPointerException(
        exceptionTemplate("version", version)
    )
}

fun Project.findVersionInt(versionInt: String): Int {
    return libs.resolveVersionInt(versionInt) ?: buildLibs.resolveVersionInt(versionInt) ?: throw NullPointerException(
        exceptionTemplate("versionInt", versionInt)
    )
}

fun Project.findDependency(dependency: String): Provider<MinimalExternalModuleDependency> {
    return libs.resolveDependency(dependency) ?: buildLibs.resolveDependency(dependency) ?: throw NullPointerException(
        exceptionTemplate("dependency", dependency)
    )
}

private fun exceptionTemplate(type: String, name: String) =
    "Couldn't resolve $type $name is it defined in the version catalog? (toml file)"
