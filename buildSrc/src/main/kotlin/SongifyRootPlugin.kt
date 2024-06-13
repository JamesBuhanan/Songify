/*
 * © 2023 Match Group, LLC.
 */

import com.songify.extension.applyOnce
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

class SongifyRootPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyOnce<JavaPlugin>()
            // applyOnce<DetektPlugin>()
            // allProjects()
            // ktLint()
            // sortDependenciesAggregated()
        }
    }
}
