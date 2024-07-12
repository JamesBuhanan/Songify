package com.songify.feature.search.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Genre(
    val caption: String,
    @DrawableRes val imageResourceId: Int,
    val backgroundColor: Color,
)
