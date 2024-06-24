import com.songify.extension.allProjects
import com.songify.extension.applyOnce
import com.songify.extension.detekt
import com.songify.extension.gradleDependenciesSorter
import com.songify.extension.kotlinJvm
import com.songify.extension.kotlinLibrary
import com.songify.extension.libs
import com.songify.extension.moshi
import com.songify.extension.retrofit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SongifyKotlinLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyOnce("kotlin")

            kotlinLibrary()
            kotlinJvm()
            allProjects()
            gradleDependenciesSorter()

            detekt()
            circuitRuntime()

            extensions.create("songify", SongifyKotlinLibraryExtension::class.java, this)
        }
    }

    private fun Project.circuitRuntime() {
        dependencies {
            "implementation"(libs.circuitRuntime)
        }
    }
}

open class SongifyKotlinLibraryExtension(private val project: Project) {
    fun moshi() = project.moshi()

    fun retrofit() = project.retrofit()
}
