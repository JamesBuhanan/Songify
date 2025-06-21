import com.songify.extension.buildCacheConfig
import com.songify.extension.dependencyResolutionManagement
import com.songify.extension.develocity
import com.songify.extension.spotlight
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class SongifySettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        with(settings) {
            dependencyResolutionManagement()
            develocity()
            spotlight()
            buildCacheConfig()

            enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

            rootProject.name = "Songify"
        }
    }
}
