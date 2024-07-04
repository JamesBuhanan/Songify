import com.android.build.gradle.AppPlugin
import com.songify.extension.allProjects
import com.songify.extension.androidApplication
import com.songify.extension.anvil
import com.songify.extension.applyOnce
import com.songify.extension.betterDynamicFeatures
import com.songify.extension.circuit
import com.songify.extension.configureLint
import com.songify.extension.detekt
import com.songify.extension.dynamicNamespace
import com.songify.extension.graphAssert
import com.songify.extension.jetpackCompose
import com.songify.extension.libs
import com.songify.extension.moduleNameFix
import com.songify.extension.moshi
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
            moduleNameFix()
            configureLint()
            detekt()
            graphAssert()
            // checkstyle()
            circuit()
            anvil()
            moshi()
            jetpackCompose()
            betterDynamicFeatures()

            dependencies {
                "implementation"(libs.timber)
            }

            extensions.create("songify", SongifyAndroidApplicationExtension::class.java, this)
        }
    }
}

open class SongifyAndroidApplicationExtension(private val project: Project) {

}
