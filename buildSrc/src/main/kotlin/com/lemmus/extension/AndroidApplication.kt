/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.androidApplication() {
    configure<ApplicationExtension> {
        kotlinAndroid(this)

        defaultConfig {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            targetSdk = findVersionInt("targetSdk")
            versionCode = 1
            versionName = "1.0"
            applicationId = "com.lemmus"
            testApplicationId = "com.lemmus.test"
        }

        aaptOptions {
            noCompress += "apk"
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

        buildConfigFields()
    }
}

fun Project.buildConfigFields() {
    configure<ApplicationExtension> {
        defaultConfig {
            // buildConfigField("int", "BUILD_NUMBER", "" + project.extra["buildNumber"] + "")
        }
    }
}
