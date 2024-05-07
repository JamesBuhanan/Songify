package com.songify.library.posts.internal.usecase

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import com.songify.library.posts.GetPostsResponse
import com.songify.library.posts.internal.PostsService
import com.songify.library.posts.usecase.GetPosts
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class GetPostsImpl @Inject constructor(
    private val postsService: PostsService
) : GetPosts {
    override suspend fun invoke(page: Int): GetPostsResponse {
        val queryMap = mapOf<String, String>(
            "type_" to "Local", /* "All" | "Local" | "Subscribed" */
            "sort" to "Active", // "Active" | "Hot" | "New" | "Old" | "TopDay" | "TopWeek" | "TopMonth" | "TopYear" | "TopAll" | "MostComments" | "NewComments"
            "page" to "$page",
//                "limit" to "",
//                "community_id" to "",
//                "community_name" to "",
//                "saved_only" to "",
//                "auth" to "",
        )
        return postsService.getPosts(queryMap)
    }
}
