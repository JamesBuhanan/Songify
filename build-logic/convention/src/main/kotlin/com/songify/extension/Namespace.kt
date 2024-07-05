package com.songify.extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.dynamicNamespace() {
    android {
        dynamicNamespace2(this)
    }
}

internal fun Project.dynamicNamespace2(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    val packageSuffix = path
        .replace("-", "")
        .replace(":", ".")
    commonExtension.namespace = "com.songify$packageSuffix"
}
