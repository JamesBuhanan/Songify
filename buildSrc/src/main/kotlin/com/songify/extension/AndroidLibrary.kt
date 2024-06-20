package com.songify.extension

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.androidLibrary() {
    configure<LibraryExtension> {
        kotlinAndroid(this)
        defaultConfig {
            targetSdk = libs.versions.targetSdk.get().toInt()
        }
        sourceSets {
            getByName("main") {
                java.srcDir("src/main/kotlin")
            }
            getByName("test") {
                java.srcDir("src/test/kotlin")
            }
        }
    }
}
