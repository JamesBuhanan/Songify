package com.songify.common.session

import com.songify.common.di.AppScope
import com.songify.common.di.SingleIn
import javax.inject.Inject

@SingleIn(AppScope::class)
class SongifySession @Inject constructor() {
    var accessToken: String? = null

    fun requireAccessToken(): String {
        return "Bearer " + requireNotNull(accessToken)
    }
}
