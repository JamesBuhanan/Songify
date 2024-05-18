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
    ":feature:posts:public", ":feature:posts:internal",
    ":feature:spotify:public", ":feature:spotify:internal",
    ":feature:splash:public", ":feature:splash:internal",
    ":library:posts:public", ":library:posts:internal",
    ":library:spotify:public", ":library:spotify:internal",

)
