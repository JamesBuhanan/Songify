package com.songify.feature.search.internal.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.songify.feature.search.internal.R

enum class Genre(
    val caption: String,
    @DrawableRes val imageResourceId: Int,
    val backgroundColor: Color,
) {
    AMBIENT("Ambient", R.drawable.genre_img_ambient, Color(0, 48, 72)),
    CHILL("Chill", R.drawable.genre_img_chill, Color(71, 126, 149)),
    CLASSICAL("Classical", R.drawable.genre_img_classical,Color(141, 103, 171)),
    DANCE("Dance", R.drawable.genre_img_dance, Color(140, 25, 50)),
    ELECTRONIC("Electronic", R.drawable.genre_img_electronic, Color(186, 93, 7)),
    METAL("Metal", R.drawable.genre_img_metal, Color(119, 119, 119)),
    RAINY_DAY("Rainy Day", R.drawable.genre_img_rainy_day, Color(144, 168, 192)),
    ROCK("Rock", R.drawable.genre_img_rock, Color(230, 30, 50)),
    PIANO("Piano", R.drawable.genre_img_piano, Color(71, 125, 149)),
    POP("Pop", R.drawable.genre_img_pop, Color(141, 103, 171)),
    SLEEP("Sleep", R.drawable.genre_img_sleep, Color(30, 50, 100))
}
