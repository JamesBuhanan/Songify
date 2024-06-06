package com.songify.library.spotify.usecase

import com.songify.library.spotify.model.Genre

interface GetGenres {
    suspend operator fun invoke(): List<Genre>
}
