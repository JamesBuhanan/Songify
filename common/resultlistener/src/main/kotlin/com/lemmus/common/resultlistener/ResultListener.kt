package com.lemmus.common.resultlistener

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.lemmus.common.di.AppScope
import com.lemmus.common.di.SingleIn
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

interface ResultListener {
    val map: MutableMap<String, MutableState<*>>
}

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class ResultListenerImpl @Inject constructor() : ResultListener {
    override val map: MutableMap<String, MutableState<*>> = mutableMapOf()
}

inline fun <reified T> ResultListener.setResult(result: T) {
    val state: MutableState<T> = map[T::class.java.name]!! as MutableState<T>
    state.value = result
}

inline fun <reified T> ResultListener.onResult(default: T): MutableState<T> {
    map.putIfAbsent(T::class.java.name, mutableStateOf(default))
    val state = map[T::class.java.name]

    return state as MutableState<T>
}