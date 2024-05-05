/*
 * © 2023 Match Group, LLC.
 */

import com.android.build.gradle.AppPlugin
import com.lemmus.extension.allProjects
import com.lemmus.extension.androidApplication
import com.lemmus.extension.anvil
import com.lemmus.extension.applyOnce
import com.lemmus.extension.circuit
import com.lemmus.extension.coreLibraryDesugaring
import com.lemmus.extension.coroutines
import com.lemmus.extension.dynamicNamespace
import com.lemmus.extension.findDependency
import com.lemmus.extension.jetpackCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class LemmusAndroidApplicationPlugin : Plugin<Project> {
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
            coreLibraryDesugaring()
            coroutines()

            dependencies {
                "implementation"(findDependency("timber"))
            }

            extensions.create("lemmus", LemmusAndroidApplicationExtension::class.java, this)
        }
    }
}

open class LemmusAndroidApplicationExtension(private val project: Project) {

}
