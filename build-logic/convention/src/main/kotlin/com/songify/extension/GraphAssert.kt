package com.songify.extension

import com.jraska.module.graph.assertion.GraphRulesExtension
import com.jraska.module.graph.assertion.ModuleGraphAssertionsPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.graphAssert() {
    applyOnce<ModuleGraphAssertionsPlugin>()

    configure<GraphRulesExtension> {
        maxHeight = 15
        restricted = arrayOf(
            // Rule 1: Feature modules cannot depend on other feature modules
            // Exceptions:
            // - :feature:foo:internal -> feature:bar:public
            // - :feature:foo:app -> :feature:foo:internal
            ":feature:\\S*:(?!app).+ -X> :feature:\\S*:(?!public).+",

            // Rule 2: Library modules cannot depend on feature modules
            // Exceptions:
            // - :library:bottom-navigation:public -> :feature:*
            ":library:(?!bottom-navigation)\\S* -X> :feature:\\S*",

            // Rule 3: Public library modules cannot depend on internal library modules
            ":library:\\S*:public\\S* -X> :library:\\S*:internal\\S*",

            // Rule 4: :feature:*:app modules are the only modules allowed to depend on internal modules.
            "^(?!.*(:feature:\\S*:app|:app))\\S* -X> \\S*:internal\\S*",
        )
        configurations = setOf(
            "api",
            "implementation",
            "debugApi",
            "debugImplementation",
            "compileOnly",
        )
    }
}
