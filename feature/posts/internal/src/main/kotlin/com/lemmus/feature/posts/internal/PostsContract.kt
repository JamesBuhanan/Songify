package com.lemmus.feature.posts.internal

import androidx.paging.PagingData
import com.lemmus.feature.posts.internal.Post.PostWithExternalLink
import com.lemmus.feature.posts.internal.Post.PostWithExternalLinkWithThumbnail
import com.lemmus.feature.posts.internal.Post.PostWithImage
import com.lemmus.feature.posts.internal.Post.PostWithJustText
import com.lemmus.library.posts.PostView
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import kotlinx.coroutines.flow.Flow

sealed interface PostsEvent : CircuitUiEvent {
    data object TappedBack : PostsEvent
}

sealed interface PostsState : CircuitUiState {
    data object Loading : PostsState

    data class Success(
        val posts: Flow<PagingData<Post>>,
        val eventSink: (PostsEvent) -> Unit,
    ) : PostsState
}

sealed class Post {
    abstract val name: String
    abstract val communityIconUrl: String?
    abstract val communityName: String
    abstract val creatorName: String
    abstract val published: String
    abstract val body: String?
    abstract val score: String
    abstract val numComments: String


    data class PostWithJustText(
        override val name: String,
        override val communityIconUrl: String?,
        override val communityName: String,
        override val creatorName: String,
        override val published: String,
        override val body: String?,
        override val score: String,
        override val numComments: String,
    ) : Post()

    data class PostWithExternalLink(
        val url: String,
        override val name: String,
        override val communityIconUrl: String?,
        override val communityName: String,
        override val creatorName: String,
        override val published: String,
        override val body: String?,
        override val score: String,
        override val numComments: String,
    ) : Post()

    data class PostWithExternalLinkWithThumbnail(
        val thumbnailUrl: String,
        val url: String,
        override val name: String,
        override val communityIconUrl: String?,
        override val communityName: String,
        override val creatorName: String,
        override val published: String,
        override val body: String?,
        override val score: String,
        override val numComments: String,
    ) : Post()

    data class PostWithImage(
        val url: String,
        override val name: String,
        override val communityIconUrl: String?,
        override val communityName: String,
        override val creatorName: String,
        override val published: String,
        override val body: String?,
        override val score: String,
        override val numComments: String,
    ) : Post()
}

fun List<PostView>.toPosts(): List<Post> {
    return map { it.toPost() }
}

fun PostView.toPost(): Post {
    return when {
        isImage(post.url) -> {
            toPostWithImage()
        }

        post.thumbnail_url == null && post.url != null -> {
            toPostWithExternalLink()
        }

        post.thumbnail_url != null && post.url != null -> {
            toPostWithExternalLinkWithThumbnail()
        }

        else -> toPostWithJustText()
    }
}

fun PostView.toPostWithImage(): PostWithImage {
    return PostWithImage(
        url = post.url!!,
        name = post.name,
        communityIconUrl = community.icon,
        communityName = community.name,
        creatorName = creator.name,
        published = covertTimeToText(post.published),
        body = if (post.body.isNullOrBlank()) null else post.body,
        score = counts.score.toString(),
        numComments = counts.numComments.toString(),
    )
}

fun PostView.toPostWithExternalLink(): PostWithExternalLink {
    return PostWithExternalLink(
        url = post.url!!,
        name = post.name,
        communityIconUrl = community.icon,
        communityName = community.name,
        creatorName = creator.name,
        published = covertTimeToText(post.published),
        body = if (post.body.isNullOrBlank()) null else post.body,
        score = counts.score.toString(),
        numComments = counts.numComments.toString(),
    )
}

fun PostView.toPostWithExternalLinkWithThumbnail(): PostWithExternalLinkWithThumbnail {
    return PostWithExternalLinkWithThumbnail(
        thumbnailUrl = post.thumbnail_url!!,
        url = post.url!!,
        name = post.name,
        communityIconUrl = community.icon,
        communityName = community.name,
        creatorName = creator.name,
        published = covertTimeToText(post.published),
        body = if (post.body.isNullOrBlank()) null else post.body,
        score = counts.score.toString(),
        numComments = counts.numComments.toString(),
    )
}

fun PostView.toPostWithJustText(): PostWithJustText {
    return PostWithJustText(
        name = post.name,
        communityIconUrl = community.icon,
        communityName = community.name,
        creatorName = creator.name,
        published = covertTimeToText(post.published),
        body = if (post.body.isNullOrBlank()) null else post.body,
        score = counts.score.toString(),
        numComments = counts.numComments.toString(),
    )
}