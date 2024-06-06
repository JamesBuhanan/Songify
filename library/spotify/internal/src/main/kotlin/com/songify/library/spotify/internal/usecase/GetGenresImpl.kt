package com.songify.library.spotify.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.library.spotify.model.Genre
import com.songify.library.spotify.usecase.GetGenres
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetGenresImpl @Inject constructor() : GetGenres {
    override suspend fun invoke(): List<Genre> = Genre.entries
}
