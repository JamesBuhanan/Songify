import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(gradleApi())
    implementation(libs.affectedModuleDetector)
    implementation(libs.androidGradlePlugin)
    implementation(libs.anvilGradlePlugin)
    implementation(libs.betterDynamicFeaturesPlugin)
//    implementation(libs.composeCompiler.gradlePlugin)
    implementation(libs.detektGradlePlugin)
    implementation(libs.develocityPlugin)
    implementation(libs.focusPlugin)
    implementation(libs.graphAssert)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(libs.kotlin.gradle)
    implementation(libs.kspGradlePlugin)
    implementation(libs.moduleGraph)
    implementation(libs.sortDependenciesGradlePlugin)

    implementation(localGroovy())
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("songifySettings") {
            id = "songify.settings"
            implementationClass = "SongifySettingsPlugin"
        }
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
        register("songifyDynamicFeature") {
            id = "songify.dynamic.feature"
            implementationClass = "SongifyDynamicFeaturePlugin"
        }
    }
}
