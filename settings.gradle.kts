pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.gradle.develocity") version "3.17.4"
    id("com.dropbox.focus") version "0.6.0"
}

//val useProjectIsolation =
//    System.getProperty("org.gradle.unsafe.isolated-projects", "false").toBoolean()
//val focusDisabled = System.getenv("NO_FOCUS").toBoolean()
//if (focusDisabled || useProjectIsolation) {
//    apply(from = "settings-all.gradle")
//} else {
//    apply(plugin = "com.dropbox.focus")
//}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Songify"

buildCache {
    local {
        isEnabled = true
        isPush = true
    }
}

develocity {
    buildScan {
        publishing.onlyIf { false }
    }
}