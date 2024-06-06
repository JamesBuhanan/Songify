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
    ":feature:home:public", ":feature:home:internal",
    ":feature:search:public", ":feature:search:internal",
    ":library:bottom-navigation:public",
    ":library:design:public",
    ":library:spotify:public", ":library:spotify:internal",
)
