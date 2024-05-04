/*
 * © 2023 Match Group, LLC.
 */

package com.lemmus.extension

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.impl.VariantOutputImpl
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.the

fun Project.androidApplication() {
    configure<ApplicationExtension> {
        kotlinAndroid(this)

        defaultConfig {
            targetSdk = findVersionInt("targetSdk")
        }
    }
}

fun Project.tinderAndroidApplication() {
    configure<ApplicationExtension> {
        defaultConfig {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            versionCode = 1
            versionName = "1.0"
            applicationId = "com.lemmus"
            testApplicationId = "com.lemmus.test"
        }

        aaptOptions {
            noCompress += "apk"
        }

        testOptions {
            unitTests {
                // Since AGP 3.0, android resources can be included in unit tests
                // See: https://github.com/robolectric/robolectric/issues/3169#issuecomment-312046322
                isIncludeAndroidResources = true
            }
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

        namespace = "com.lemmus"
    }
}

fun Project.buildConfigFields() {
    configure<ApplicationExtension> {
        defaultConfig {
            // buildConfigField("int", "BUILD_NUMBER", "" + project.extra["buildNumber"] + "")
        }
    }
}
