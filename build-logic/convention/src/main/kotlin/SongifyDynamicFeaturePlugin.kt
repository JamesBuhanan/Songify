import com.android.build.gradle.DynamicFeaturePlugin
import com.songify.extension.allProjects
import com.songify.extension.androidTest
import com.songify.extension.anvil
import com.songify.extension.applyOnce
import com.songify.extension.betterDynamicFeatures
import com.songify.extension.circuit
import com.songify.extension.configureLint
import com.songify.extension.coroutines
import com.songify.extension.detekt
import com.songify.extension.dynamicFeatureModule
import com.songify.extension.dynamicNamespace
import com.songify.extension.gradleDependenciesSorter
import com.songify.extension.jetpackCompose
import com.songify.extension.libs
import com.songify.extension.moduleNameFix
import com.songify.extension.moshi
import com.songify.extension.parcelize
import com.songify.extension.retrofit
import com.songify.extension.test
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SongifyDynamicFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyOnce<DynamicFeaturePlugin>()
            betterDynamicFeatures()
            applyOnce("kotlin-android")

            dynamicFeatureModule()
            allProjects()
            moduleNameFix()

            detekt()
            // checkstyle()
            configureLint()
            gradleDependenciesSorter()
            dynamicNamespace()
            coroutines()

            dependencies {
                "implementation"(libs.timber)
                "implementation"(project(":app"))
            }

            extensions.create("songify", SongifyDynamicFeatureExtension::class.java, this)
        }
    }
}

open class SongifyDynamicFeatureExtension(private val project: Project) {
    fun anvil() = project.anvil()
    fun circuit() = project.circuit()
    fun coroutines() = project.coroutines()
    fun jetpackCompose() = project.jetpackCompose()
    fun moshi() = project.moshi()
    fun parcelize() = project.parcelize()
    fun retrofit() = project.retrofit()
    fun test() = project.test()
    fun androidTest() = project.androidTest()
}
