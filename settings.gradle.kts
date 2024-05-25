enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Songify"

buildCache {
    local {
        isEnabled = true
        isPush = true
    }
}

include(
    ":app",
    ":common:coroutines",
    ":common:di",
    ":common:retrofit",
    ":common:session",
    ":common:theme",
    ":common:ui",
    ":feature:spotify:public", ":feature:spotify:internal",
    ":library:bottom-navigation:public",
    ":library:spotify:public", ":library:spotify:internal",
)
