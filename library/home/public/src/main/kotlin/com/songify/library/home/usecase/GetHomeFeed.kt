package com.songify.library.home.usecase

import com.songify.library.home.model.HomeFeed

interface GetHomeFeed {
    suspend operator fun invoke(): Result<HomeFeed>
}