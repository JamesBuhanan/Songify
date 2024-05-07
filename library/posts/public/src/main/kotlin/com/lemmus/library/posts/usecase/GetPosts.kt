package com.songify.library.posts.usecase

import com.songify.library.posts.GetPostsResponse

interface GetPosts {
    suspend operator fun invoke(page: Int): GetPostsResponse
}