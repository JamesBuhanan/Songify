package com.songify.extension

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.androidApplication() {
    configure<ApplicationExtension> {
        kotlinAndroid(this)

        signingConfigs {
            maybeCreate("debug").apply {
                storeFile = file("debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
            }
        }

        buildTypes {
            debug {
                isMinifyEnabled = false
                isDebuggable = true
                signingConfig = signingConfigs.getByName("debug")
            }
        }

        defaultConfig {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            targetSdk = libs.versions.targetSdk.get().toInt()
            versionCode = 1
            versionName = "1.0"
            applicationId = "com.songify"
            testApplicationId = "com.songify.test"
            manifestPlaceholders.putAll(
                mapOf(
                    "redirectSchemeName" to "songify",
                    "redirectHostName" to "callback"
                )
            )
        }

        testOptions {
            unitTests.all {
                // Explicitly disable stacktrace recovery during test runs so that tests can rely on
                // Exception referential equality when asserting that the actual Exception thrown is equal
                // to the expected Exception.
                // For more info, please see:
                //   - https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/debugging.md#stacktrace-recovery
                //   - https://github.com/Kotlin/kotlinx.coroutines/issues/921
                it.systemProperty("kotlinx.coroutines.stacktrace.recovery", "false")
            }

            execution = "ANDROIDX_TEST_ORCHESTRATOR"
        }
    }
}
