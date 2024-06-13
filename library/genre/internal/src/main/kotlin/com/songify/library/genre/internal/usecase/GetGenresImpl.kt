package com.songify.library.genre.internal.usecase

import androidx.compose.ui.graphics.Color
import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.library.genre.Genre
import com.songify.library.genre.internal.R
import com.songify.library.genre.usecase.GetGenres
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetGenresImpl @Inject constructor() : GetGenres {
    override fun invoke(): List<Genre> = listOf(
        Genre("Ambient", R.drawable.genre_img_ambient, Color(0, 48, 72)),
        Genre("Chill", R.drawable.genre_img_chill, Color(71, 126, 149)),
        Genre("Classical", R.drawable.genre_img_classical, Color(141, 103, 171)),
        Genre("Dance", R.drawable.genre_img_dance, Color(140, 25, 50)),
        Genre("Electronic", R.drawable.genre_img_electronic, Color(186, 93, 7)),
        Genre("Metal", R.drawable.genre_img_metal, Color(119, 119, 119)),
        Genre("Rainy Day", R.drawable.genre_img_rainy_day, Color(144, 168, 192)),
        Genre("Rock", R.drawable.genre_img_rock, Color(230, 30, 50)),
        Genre("Piano", R.drawable.genre_img_piano, Color(71, 125, 149)),
        Genre("Pop", R.drawable.genre_img_pop, Color(141, 103, 171)),
        Genre("Sleep", R.drawable.genre_img_sleep, Color(30, 50, 100))
    )
}
