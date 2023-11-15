package com.lemmus.library.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostAggregates(
    val id: Int,
    val post_id: PostId,
    @Json(name = "comments")
    val numComments: Int,
    val score: Int,
    val upvotes: Int,
    val downvotes: Int,
    val published: String,
    val newest_comment_time_necro: String,
    val newest_comment_time: String,
    val featured_community: Boolean,
    val featured_local: Boolean,
)
