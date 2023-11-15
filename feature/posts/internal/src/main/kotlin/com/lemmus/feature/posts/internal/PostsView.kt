package com.lemmus.feature.posts.internal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.lemmus.common.di.AppScope
import com.lemmus.common.theme.LemmusTheme
import com.lemmus.common.theme.MEDIUM_PADDING
import com.lemmus.common.theme.SMALL_PADDING
import com.lemmus.common.theme.THUMBNAIL_SIZE
import com.lemmus.common.ui.LoadingBar
import com.lemmus.feature.posts.PostsScreen
import com.lemmus.feature.posts.internal.Post.PostWithExternalLink
import com.lemmus.feature.posts.internal.Post.PostWithExternalLinkWithThumbnail
import com.lemmus.feature.posts.internal.Post.PostWithImage
import com.lemmus.feature.posts.internal.Post.PostWithJustText
import com.lemmus.feature.posts.internal.PostsConstants.POST_LIST_TAG
import com.lemmus.feature.posts.internal.common.getImageRequest
import com.slack.circuit.codegen.annotations.CircuitInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal object PostsConstants {
    const val POST_LIST_TAG = "grid"
}

@CircuitInject(PostsScreen::class, AppScope::class)
@Composable
fun PostsView(
    postsState: PostsState,
    modifier: Modifier = Modifier,
) {
    when (postsState) {
        is PostsState.Loading -> LoadingBar()
        is PostsState.Success -> ShowPosts(postsState)
    }
}

@Composable
fun ShowPosts(postsState: PostsState.Success) {
    BackHandler {
        postsState.eventSink(PostsEvent.TappedBack)
    }
    LemmusNavigationDrawer {
        Box(modifier = Modifier.padding(it)) {
            PostItemList(
                posts = postsState.posts,
                onEvent = postsState.eventSink,
            )
        }
    }
}

@Composable
fun PostItemList(
    posts: Flow<PagingData<Post>>,
    onEvent: (PostsEvent) -> Unit = {},
) {
    val lazyPosts: LazyPagingItems<Post> = posts.collectAsLazyPagingItems()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
        modifier = Modifier
            .testTag(POST_LIST_TAG)
            .fillMaxWidth(),
    ) {
        items(
            count = lazyPosts.itemCount,
            key = lazyPosts.itemKey { it.name },
        ) { index ->
            val item = lazyPosts[index]!!
            PostItem(
                post = item,
                onEvent = onEvent,
            )
        }
    }
}

@Preview
@Composable
fun PostItemListPreview() {
    LemmusTheme {
        PostItemList(
            posts = flow {
                PagingData.from(
                    listOf(
                        samplePostView.toPost(),
                        sampleLinkNoThumbnailPostView.toPost(),
                        sampleLinkPostView.toPost(),
                        sampleImagePostView.toPost(),
                    )
                )
            }
        )
    }
}

@Composable
fun PostItem(
    post: Post,
    onEvent: (PostsEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(MEDIUM_PADDING)
            .fillMaxWidth()
    )
    {
        HeaderRow(
            communityIconUrl = post.communityIconUrl,
            communityName = post.communityName,
            creatorName = post.creatorName,
            published = post.published,
        )

        when (post) {
            is PostWithExternalLink -> PostWithExternalLinkView(post)
            is PostWithExternalLinkWithThumbnail -> PostWithExternalLinkWithThumbnailView(post)
            is PostWithImage -> PostWithImageView(post)
            is PostWithJustText -> PostWithJustTextView(post)
        }

        post.body?.let { body ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(5.dp, 5.dp, 10.dp, 5.dp),
            ) {
                Text(
                    text = body,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        FooterRow(
            score = post.score,
            numComments = post.numComments,
        )
    }
}

@Composable
fun PostWithExternalLinkView(
    postWithExternalLink: PostWithExternalLink
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (text, box) = createRefs()
        Text(
            text = postWithExternalLink.name,
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    end.linkTo(box.start, margin = MEDIUM_PADDING)
                    width = Dimension.fillToConstraints
                }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(75.dp)
                .width(75.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .constrainAs(box) {
                    end.linkTo(parent.end)
                }
        ) {
            Icon(Icons.Filled.Link, "Upvote")
        }
    }
}

@Composable
fun PostWithExternalLinkWithThumbnailView(
    postWithExternalLinkWithThumbnail: PostWithExternalLinkWithThumbnail
) {
    Text(text = postWithExternalLinkWithThumbnail.name)
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        AsyncImage(
            model = postWithExternalLinkWithThumbnail.thumbnailUrl,
            placeholder = painterResource(R.drawable.ic_android_black_24dp),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            filterQuality = FilterQuality.High,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = postWithExternalLinkWithThumbnail.url,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            modifier = Modifier.padding(MEDIUM_PADDING)
        )
    }
}

@Composable
fun PostWithImageView(postWithImage: PostWithImage) {
    Text(text = postWithImage.name)
    AsyncImage(
        model = postWithImage.url,
        placeholder = painterResource(R.drawable.ic_android_black_24dp),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        filterQuality = FilterQuality.High,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun PostWithJustTextView(
    postWithJustText: PostWithJustText
) {
    Text(text = postWithJustText.name)
}

@Composable
fun HeaderRow(
    communityIconUrl: String?,
    communityName: String,
    creatorName: String,
    published: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CommunityIcon(communityIconUrl)
        Column {
            Text(communityName)
            Row(
                horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
            ) {
                Text(creatorName)
                Text("•")
                Text(published)
            }
        }
    }
}

@Composable
fun CommunityIcon(
    communityIconUrl: String?,
) {
    if (communityIconUrl != null) {
        AsyncImage(
            model = getImageRequest(
                context = LocalContext.current,
                path = communityIconUrl,
                size = THUMBNAIL_SIZE,
                blur = false,
            ),
            placeholder = painterResource(R.drawable.ic_android_black_24dp),
            contentDescription = "User Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp) // Add padding around the circular icon
                .clip(CircleShape)
        )
    } else {
        Image(
            painter = painterResource(R.drawable.ic_android_black_24dp),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp) // Add padding around the circular icon
                .clip(CircleShape)
        )
    }
}

@Composable
fun VoteBox(
    score: String,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(5.dp, 5.dp, 5.dp, 5.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                SMALL_PADDING,
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.ArrowUpward, "Upvote")
            Text(score)
            Icon(Icons.Filled.ArrowDownward, "Downvote")
        }
    }
}

@Composable
fun CommentBox(
    numComments: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(5.dp, 5.dp, 10.dp, 5.dp),
    ) {
        // TODO Center text and we won't need 10dp padding on one side
        Row(
            horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Comment, "Comment")
            Text(numComments)
        }
    }
}

@Composable
fun FooterRow(
    score: String,
    numComments: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        CommentBox(numComments)
        VoteBox(score)
    }
}
