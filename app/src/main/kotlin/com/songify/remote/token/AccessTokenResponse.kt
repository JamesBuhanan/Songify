package com.example.musify.data.remote.token

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

/**
 * A response object that contains the [accessToken] for any specific api.
 * It also contains additional information such as the [secondsUntilExpiration]
 * and the [tokenType].
 */
data class AccessTokenResponse(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("expires_in") val secondsUntilExpiration: Int,
    @JsonProperty("token_type") val tokenType: String
)

/**
 * A mapper function for converting an instance of [AccessTokenResponse]
 * to an instance of [BearerToken].
 */
fun AccessTokenResponse.toBearerToken() = BearerToken(
    tokenString = accessToken,
    timeOfCreation = LocalDateTime.now(),
    secondsUntilExpiration = secondsUntilExpiration
)