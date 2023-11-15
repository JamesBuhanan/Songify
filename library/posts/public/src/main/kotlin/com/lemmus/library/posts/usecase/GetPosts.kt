package com.lemmus.library.posts.usecase

import com.lemmus.library.posts.GetPostsResponse

interface GetPosts {
    suspend operator fun invoke(page: Int): GetPostsResponse
}