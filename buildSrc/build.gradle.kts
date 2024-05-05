plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(libs.androidGradlePlugin)
    implementation(libs.anvil.gradlePlugin)
    implementation(libs.kotlin.gradle)
    implementation(libs.kspGradlePlugin)
    implementation(localGroovy())
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
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
