import com.songify.extension.affectedModules
import com.songify.extension.applyOnce
import com.songify.extension.checkSortDependenciesAggregated
import com.songify.extension.detekt
import com.songify.extension.moduleGraph
import com.songify.extension.sortDependenciesAggregated
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

class SongifyRootPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyOnce<JavaPlugin>()
            // ktLint()
            detekt()
            affectedModules()
            sortDependenciesAggregated()
            checkSortDependenciesAggregated()
            moduleGraph()
        }
    }
}
