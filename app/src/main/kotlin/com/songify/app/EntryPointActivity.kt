package com.songify.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.songify.common.di.ActivityKey
import com.songify.common.di.AppScope
import com.songify.common.session.SongifySession
import com.songify.common.theme.SongifyTheme
import com.songify.feature.splash.SplashScreen
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

const val CLIENT_ID = "9ec02e7f10514c15842363d73f64f985"
const val AUTH_TOKEN_REQUEST_CODE = 1337

@ContributesMultibinding(AppScope::class, boundType = Activity::class)
@ActivityKey(EntryPointActivity::class)
class EntryPointActivity @Inject constructor(
    private val circuit: Circuit,
    private val songifySession: SongifySession,
) : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchToken()

        setContent {
            val backStack = rememberSaveableBackStack(SplashScreen)
            val navigator = rememberCircuitNavigator(backStack)

            SongifyTheme {
                CircuitCompositionLocals(circuit) {
                    NavigableCircuitContent(navigator, backStack)
                }
            }
        }
    }

    // val scopes = arrayOf(
    //     "playlist-read-private",
    //     "playlist-read-collaborative",
    //     "streaming",
    //     "user-library-read",
    //     "user-read-private",
    //     "user-top-read"
    // )
    private fun fetchToken() {
        val request = AuthorizationRequest.Builder(
            CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            "songify://callback"
        )
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-email"))
            .setCampaign("your-campaign-token")
            .build()

        AuthorizationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthorizationClient.getResponse(resultCode, data)
        if (requestCode == AUTH_TOKEN_REQUEST_CODE) {
            songifySession.accessToken = response.accessToken
        }
    }
}
