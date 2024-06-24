/*
 * © 2023 Match Group, LLC.
 */
//import com.songify.extension.develocity
import com.songify.extension.dependencyResolutionManagement
import com.songify.extension.focus
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class SongifySettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        with(settings) {
            dependencyResolutionManagement()

//            develocity()
            focus()

            enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

            rootProject.name = "Songify"

            buildCache {
                local {
                    isEnabled = true
                    isPush = true
                }
            }
        }
    }
}
