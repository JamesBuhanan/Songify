/*
 * © 2023 Match Group, LLC.
 */

import com.android.build.gradle.AppPlugin
import com.songify.extension.allProjects
import com.songify.extension.androidApplication
import com.songify.extension.anvil
import com.songify.extension.applyOnce
import com.songify.extension.circuit
import com.songify.extension.coroutines
import com.songify.extension.dynamicNamespace
import com.songify.extension.jetpackCompose
import com.songify.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SongifyAndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyOnce<AppPlugin>()
            applyOnce("kotlin-android")

            androidApplication()
            allProjects()
            dynamicNamespace()

            // configureLint()
            // detekt()
            // checkstyle()
            // gradleDependenciesSorter()
            circuit()
            anvil()
            jetpackCompose()
            coroutines()

            dependencies {
                "implementation"(libs.timber)
            }

            extensions.create("songify", SongifyAndroidApplicationExtension::class.java, this)
        }
    }
}

open class SongifyAndroidApplicationExtension(private val project: Project) {

}
