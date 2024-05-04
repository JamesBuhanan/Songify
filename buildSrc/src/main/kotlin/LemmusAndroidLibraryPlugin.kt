/*
 * © 2023 Match Group, LLC.
 */

import com.android.build.gradle.LibraryPlugin
import com.lemmus.extension.allProjects
import com.lemmus.extension.androidLibrary
import com.lemmus.extension.anvil
import com.lemmus.extension.applyOnce
import com.lemmus.extension.coreLibraryDesugaring
import com.lemmus.extension.dynamicNamespace
import com.lemmus.extension.jetpackCompose
import com.lemmus.extension.moshi
import org.gradle.api.Plugin
import org.gradle.api.Project

class LemmusAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyOnce<LibraryPlugin>()
            applyOnce("kotlin-android")

            androidLibrary()
            allProjects()

            // detekt()
            // checkstyle()
            // configureLint()
            // gradleDependenciesSorter()
            coreLibraryDesugaring()
            dynamicNamespace()

            extensions.create("lemmus", LemmusAndroidLibraryExtension::class.java, this)
        }
    }
}

open class LemmusAndroidLibraryExtension(private val project: Project) {
    fun anvil() = project.anvil()
    fun moshi() = project.moshi()
//
//    fun glide() = project.glide()
//
    fun jetpackCompose() = project.jetpackCompose()
}
