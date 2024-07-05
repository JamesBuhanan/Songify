package com.songify.library.session

import com.songify.library.di.AppScope
import javax.inject.Inject

@AppScope
class SongifySession @Inject constructor() {
    var accessToken: String? = null

    fun requireAccessToken(): String {
        return "Bearer " + requireNotNull(accessToken) { "SongifySession.accessToken is null!" }
    }
}
