package com.lemmus.library.posts

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPostsResponse(
    val posts: List<PostView>,
)
