plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlin.gradle)
    implementation(gradleApi())
    implementation(localGroovy())
}

gradlePlugin {
    plugins {
        register("lemmusSettings") {
            id = "lemmus.settings"
            implementationClass = "LemmusSettingsPlugin"
        }
        register("lemmusRoot") {
            id = "lemmus.root"
            implementationClass = "LemmusRootPlugin"
        }
        register("lemmusAndroidApplication") {
            id = "lemmus.android.application"
            implementationClass = "LemmusAndroidApplicationPlugin"
        }
        register("lemmusAndroidLibrary") {
            id = "lemmus.android.library"
            implementationClass = "LemmusAndroidLibraryPlugin"
        }
        register("lemmusKotlinLibrary") {
            id = "lemmus.kotlin.library"
            implementationClass = "LemmusKotlinLibraryPlugin"
        }
    }
}
