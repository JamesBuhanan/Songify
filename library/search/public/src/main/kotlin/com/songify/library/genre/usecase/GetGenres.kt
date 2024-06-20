package com.songify.library.genre.usecase

import com.songify.library.genre.Genre

interface GetGenres {
    operator fun invoke(): List<Genre>
}