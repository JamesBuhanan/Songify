package com.songify.extension

import com.android.build.api.dsl.DynamicFeatureExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.dynamicFeatureModule() {
    configure<DynamicFeatureExtension> {
        buildTypes {
            debug {
                isShrinkResources = false
                isMinifyEnabled = false
            }
        }

        kotlinAndroid(this)

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
