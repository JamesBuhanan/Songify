package com.lemmus.feature.posts.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.lemmus.common.di.AppScope
import com.lemmus.feature.posts.PostsScreen
import com.lemmus.feature.posts.internal.paging.PostsPagingSource
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PostsPresenter @AssistedInject constructor(
    private val postsPagingSource: PostsPagingSource,
    @Assisted private val navigator: Navigator,
) : Presenter<PostsState> {
    @Composable
    override fun present(): PostsState {

        val state by produceState<PostsState>(PostsState.Loading) {
            value = PostsState.Success(
                posts = Pager(
                    config = PagingConfig(
                        pageSize = 20,
                    ),
                    pagingSourceFactory = {
                        postsPagingSource
                    }
                ).flow,
                eventSink = {
                    when (it) {
                        PostsEvent.TappedBack -> navigator.pop()
                    }
                }
            )
        }

        return state
    }

    @CircuitInject(PostsScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): PostsPresenter
    }
}

