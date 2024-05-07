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
    ":common:theme",
    ":common:ui",
    ":feature:posts:public", ":feature:posts:internal",
    ":feature:splash:public", ":feature:splash:internal",
    ":library:posts:public", ":library:posts:internal",
)
