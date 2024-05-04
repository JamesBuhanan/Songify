/*
 * © 2023 Match Group, LLC.
 */
import com.lemmus.extension.buildCacheConfig
import com.lemmus.extension.dependencyResolutionManagement
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class LemmusSettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        with(settings) {
            dependencyResolutionManagement()

            buildCacheConfig()

            enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

            rootProject.name = "Lemmus"
        }
    }
}
