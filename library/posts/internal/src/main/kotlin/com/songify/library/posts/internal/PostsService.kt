package com.songify.library.posts.internal

import com.songify.library.posts.GetPostResponse
import com.songify.library.posts.GetPostsResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PostsService {
    @GET("post/list")
    suspend fun getPosts(@QueryMap form: Map<String, String>): GetPostsResponse

    @GET("post")
    suspend fun getPost(@QueryMap form: Map<String, String>): GetPostResponse
}
