package com.songify.library.composeextensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.internal.StableCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow

@Composable
inline fun <T : Any> Flow<PagingData<T>>.rememberRetainedCachedPagingFlow(
    scope: CoroutineScope = rememberRetainedCoroutineScope(),
): Flow<PagingData<T>> = rememberRetained(this, scope) { cachedIn(scope) }

@Composable
fun rememberRetainedCoroutineScope(): StableCoroutineScope {
    return rememberRetained("coroutine_scope") {
        object : RememberObserver {
            val scope = StableCoroutineScope(CoroutineScope(Dispatchers.Main + Job()))

            override fun onAbandoned() = onForgotten()

            override fun onForgotten() {
                scope.cancel()
            }

            override fun onRemembered() = Unit
        }
    }.scope
}