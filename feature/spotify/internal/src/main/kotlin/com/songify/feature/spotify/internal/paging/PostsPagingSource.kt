//package com.songify.feature.spotify.internal.paging
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.songify.common.di.AppScope
//import com.songify.common.di.SingleIn
//import com.songify.feature.posts.internal.Post
//import com.songify.feature.posts.internal.toPosts
//import com.songify.library.posts.usecase.GetPosts
//import javax.inject.Inject
//
//@SingleIn(AppScope::class)
//class PostsPagingSource @Inject constructor(
//    private val getPosts: GetPosts,
//) : PagingSource<Int, Post>() {
//    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
//        return try {
//            val page = params.key ?: 1
//            val response = getPosts(page)
//
//            LoadResult.Page(
//                data = response.posts.toPosts(),
//                prevKey = if (page == 1) null else page.minus(1),
//                nextKey = if (response.posts.isEmpty()) null else page.plus(1),
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}