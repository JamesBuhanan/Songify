package com.songify.library.spotify.usecase

import com.songify.library.spotify.model.HomeFeed

interface GetHomeFeed {
    suspend operator fun invoke(): Result<HomeFeed>
}