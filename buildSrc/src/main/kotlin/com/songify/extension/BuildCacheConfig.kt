/*
 * © 2023 Match Group, LLC.
 */

package com.songify.extension

import org.gradle.api.initialization.Settings

fun Settings.buildCacheConfig() {
    buildCache {
        local {
            isEnabled = true
            isPush = true
        }
    }
}
