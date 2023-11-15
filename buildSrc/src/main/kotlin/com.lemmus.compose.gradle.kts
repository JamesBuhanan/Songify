@file:Suppress("DEPRECATION")

import com.android.build.gradle.BaseExtension

with(the<BaseExtension>()) {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = libs.resolveVersion("composeCompiler").requiredVersion
    }
}
