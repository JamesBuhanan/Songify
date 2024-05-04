/*
 * © 2023 Match Group, LLC.
 */

import com.android.build.gradle.AppPlugin
import com.lemmus.extension.allProjects
import com.lemmus.extension.androidApplication
import com.lemmus.extension.applyOnce
import org.gradle.api.Plugin
import org.gradle.api.Project

class LemmusAndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyOnce<AppPlugin>()
            applyOnce("kotlin-android")

            androidApplication()
            allProjects()

            // configureLint()
            // detekt()
            // checkstyle()
            // gradleDependenciesSorter()

            extensions.create("tinder", LemmusAndroidApplicationExtension::class.java, this)
        }
    }
}

open class LemmusAndroidApplicationExtension(private val project: Project) {
    fun anvil() = project.anvil()

    fun jetpackCompose() = project.jetpackCompose()
    fun jetpackCompose(useComposeCompiler: Boolean) = project.jetpackCompose(useComposeCompiler)

    fun moshi() = project.moshi()

    fun coreLibraryDesugaring() = project.coreLibraryDesugaring()
}
