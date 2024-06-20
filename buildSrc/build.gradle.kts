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
    implementation(libs.affectedModuleDetector)
    implementation(libs.androidGradlePlugin)
    implementation(libs.composeCompiler.gradlePlugin)
    implementation(libs.detektGradlePlugin)
    implementation(libs.graphAssert)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(libs.kotlin.gradle)
    implementation(libs.kspGradlePlugin)
    implementation(libs.sortDependenciesGradlePlugin)

    implementation(localGroovy())
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("songifyRoot") {
            id = "songify.root"
            implementationClass = "SongifyRootPlugin"
        }
        register("songifyAndroidApplication") {
            id = "songify.android.application"
            implementationClass = "SongifyAndroidApplicationPlugin"
        }
        register("songifyAndroidLibrary") {
            id = "songify.android.library"
            implementationClass = "SongifyAndroidLibraryPlugin"
        }
        register("songifyKotlinLibrary") {
            id = "songify.kotlin.library"
            implementationClass = "SongifyKotlinLibraryPlugin"
        }
    }
}
