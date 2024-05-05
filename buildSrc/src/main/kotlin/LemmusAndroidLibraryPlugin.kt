/*
 * © 2023 Match Group, LLC.
 */

import com.android.build.gradle.LibraryPlugin
import com.lemmus.extension.allProjects
import com.lemmus.extension.androidLibrary
import com.lemmus.extension.anvil
import com.lemmus.extension.applyOnce
import com.lemmus.extension.circuit
import com.lemmus.extension.coroutines
import com.lemmus.extension.dynamicNamespace
import com.lemmus.extension.feature
import com.lemmus.extension.jetpackCompose
import com.lemmus.extension.libs
import com.lemmus.extension.moshi
import com.lemmus.extension.retrofit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

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
            dynamicNamespace()
            coroutines()

            dependencies {
                "implementation"(libs.timber)
            }

            extensions.create("lemmus", LemmusAndroidLibraryExtension::class.java, this)
        }
    }
}

open class LemmusAndroidLibraryExtension(private val project: Project) {
    fun anvil() = project.anvil()

    fun moshi() = project.moshi()

    // fun glide() = project.glide()

    fun jetpackCompose() = project.jetpackCompose()

    fun circuit() = project.circuit()

    fun retrofit() = project.retrofit()

    fun feature() = project.feature()

    fun coroutines() = project.coroutines()
}
