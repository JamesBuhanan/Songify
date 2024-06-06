/*
 * © 2023 Match Group, LLC.
 */

import com.android.build.gradle.LibraryPlugin
import com.songify.extension.allProjects
import com.songify.extension.androidLibrary
import com.songify.extension.anvil
import com.songify.extension.applyOnce
import com.songify.extension.circuit
import com.songify.extension.coroutines
import com.songify.extension.dynamicNamespace
import com.songify.extension.feature
import com.songify.extension.jetpackCompose
import com.songify.extension.libs
import com.songify.extension.moduleNameFix
import com.songify.extension.moshi
import com.songify.extension.retrofit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SongifyAndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyOnce<LibraryPlugin>()
            applyOnce("kotlin-android")

            androidLibrary()
            allProjects()
            moduleNameFix()

            // detekt()
            // checkstyle()
            // configureLint()
            // gradleDependenciesSorter()
            dynamicNamespace()
            coroutines()

            dependencies {
                "implementation"(libs.timber)
            }

            extensions.create("songify", SongifyAndroidLibraryExtension::class.java, this)
        }
    }
}

open class SongifyAndroidLibraryExtension(private val project: Project) {
    fun anvil() = project.anvil()

    fun moshi() = project.moshi()

    // fun glide() = project.glide()

    fun jetpackCompose() = project.jetpackCompose()

    fun circuit() = project.circuit()

    fun retrofit() = project.retrofit()

    fun feature() = project.feature()

    fun coroutines() = project.coroutines()
}
