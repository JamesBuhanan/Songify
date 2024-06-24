package com.songify.extension

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

internal fun Project.dynamicNamespace() {
    extensions.findByType(LibraryExtension::class)?.apply {
        dynamicNamespace2(this)
    }
    extensions.findByType(ApplicationExtension::class)?.apply {
        dynamicNamespace2(this)
    }
}

internal fun Project.dynamicNamespace2(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    val packageSuffix = path
        .replace(":public", "")
        .replace("-","")
        .replace(":", ".")
    commonExtension.namespace = "com.songify$packageSuffix"
}
