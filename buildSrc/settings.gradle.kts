// Rename the main build file from build.gradle to buildSrc.gradle
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

buildCache {
    local {
        isEnabled = true
        isPush = true
    }
}
