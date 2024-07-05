package com.songify.library.spotify.internal.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * A sealed class that contains the logic to manage keys for a paginated
 * stream of type [V], from the Spotify API. The [loadBlock] can be used
 * to define what is to be loaded. The lambda will be provided with the
 * limit, and offset. The caller just needs to define how to fetch the required type,
 * taking care of handling any exceptions. The keys will be completely managed
 * by this class.
 */
sealed class SpotifyPagingSource<V : Any>(
    private val loadBlock: suspend (
        limit: Int,
        offset: Int
    ) -> Result<List<V>>
) : PagingSource<Int, V>() {

    override fun getRefreshKey(state: PagingState<Int, V>): Int? = state.anchorPosition
        ?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val pageNumber = params.key ?: 0
        loadBlock(
            params.loadSize.coerceAtMost(50), // Spotify API doesn't allow 'limit' to exceed 50
            params.loadSize * pageNumber
        ).fold(
            { loadedList ->
                return LoadResult.Page(
                    data = loadedList,
                    prevKey = if (pageNumber == 0) null else pageNumber - 1,
                    nextKey = if (loadedList.isEmpty()) null else pageNumber + 1,
                )
            },
            {
                return LoadResult.Error(it)
            }
        )
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}