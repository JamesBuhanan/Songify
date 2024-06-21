package com.songify.common.session

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongifySession @Inject constructor() {
    var accessToken: String? = null

    fun requireAccessToken(): String {
        return "Bearer " + requireNotNull(accessToken)
    }
}
