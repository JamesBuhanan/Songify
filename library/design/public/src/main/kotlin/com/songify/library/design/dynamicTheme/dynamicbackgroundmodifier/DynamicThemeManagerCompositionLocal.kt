package com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.songify.library.design.dynamicTheme.manager.DynamicThemeManager
import com.songify.library.design.dynamicTheme.manager.DynamicThemeManagerImpl
import com.songify.library.design.usecase.GetDrawableFromUrl
import kotlinx.coroutines.Dispatchers

val LocalDynamicThemeManager: ProvidableCompositionLocal<DynamicThemeManager> =
    staticCompositionLocalOf {
        DynamicThemeManagerImpl(
            downloadDrawableFromUrlUseCase = GetDrawableFromUrl(Dispatchers.IO),
            defaultDispatcher = Dispatchers.IO
        )
    }
