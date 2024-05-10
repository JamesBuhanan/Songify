package com.songify.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.songify.common.di.ActivityKey
import com.songify.common.di.AppScope
import com.songify.common.theme.SongifyTheme
import com.songify.feature.splash.SplashScreen
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.squareup.anvil.annotations.ContributesMultibinding
import timber.log.Timber
import javax.inject.Inject

const val CLIENT_ID = "ff860a635b3545b5b9bdb8b30821deca"
const val AUTH_TOKEN_REQUEST_CODE = 0x10

@ContributesMultibinding(AppScope::class, boundType = Activity::class)
@ActivityKey(EntryPointActivity::class)
class EntryPointActivity @Inject constructor(
    private val circuit: Circuit
) : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val backStack = rememberSaveableBackStack(SplashScreen)
            val navigator = rememberCircuitNavigator(backStack)

            SongifyTheme {
//                CircuitCompositionLocals(circuit) {
//                    NavigableCircuitContent(navigator, backStack)
//                }
                Text("TEST")
            }
        }

        onRequestTokenClicked()
    }

    private fun onRequestTokenClicked() {
        val request: AuthorizationRequest =
            getAuthenticationRequest(AuthorizationResponse.Type.TOKEN)
        AuthorizationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)
    }

    private fun getAuthenticationRequest(type: AuthorizationResponse.Type): AuthorizationRequest {
//        val scopes = arrayOf(
//            "playlist-read-private",
//            "playlist-read-collaborative",
//            "streaming",
//            "user-library-read",
//            "user-read-private",
//            "user-top-read"
//        )
        return AuthorizationRequest.Builder(CLIENT_ID, type, "Songify://callback")
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-email"))
            //.setCampaign("your-campaign-token")
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthorizationClient.getResponse(resultCode, data)
        if (requestCode == AUTH_TOKEN_REQUEST_CODE) {
            Timber.e(response.accessToken)
        }
    }
}
