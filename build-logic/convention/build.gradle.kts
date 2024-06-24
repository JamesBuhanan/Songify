/*
 * © 2023 Match Group, LLC.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `groovy`
    `maven-publish`
//    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.songify.buildlogic"
version = "1.0.0"

//publishing {
//    publications {
//        create<MavenPublication>("shadow") {
//            val binary = project.file("${project.buildDir}/libs/convention-$version.jar")
//            val binaryArtifact = project.artifacts.add("archives", binary) {
//                builtBy("shadowJar")
//            }
//            artifact(binaryArtifact)
//        }
//    }
//    repositories {
//        mavenLocal()
//    }
//}
//
//tasks.named<ShadowJar>("shadowJar") {
//    isZip64 = true // https://github.com/johnrengelman/shadow/issues/107
//    dependsOn("jar")
//    archiveBaseName.set("convention")
//    archiveClassifier.set("")
//    archiveVersion.set(version.toString())
//}

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
//    implementation(libs.graphAssert)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(libs.kotlin.gradle)
    implementation(libs.kspGradlePlugin)
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
    }
}
