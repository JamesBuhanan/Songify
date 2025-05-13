package com.songify.library.home.fake.usecase

import com.songify.library.home.fake.TestData.fakeHomeFeed
import com.songify.library.home.usecase.GetHomeFeed

object FakeGetHomeFeed : GetHomeFeed {
    override suspend operator fun invoke() = Result.success(fakeHomeFeed)
}
