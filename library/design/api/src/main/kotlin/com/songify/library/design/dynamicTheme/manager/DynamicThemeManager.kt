package com.songify.library.design.dynamicTheme.manager

import android.content.Context
import androidx.compose.ui.graphics.Color

interface DynamicThemeManager {
    suspend fun getBackgroundColorForImageFromUrl(
        url: String,
        context: Context
    ): Color?
}
