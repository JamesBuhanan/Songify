package com.songify.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.screen.Screen
import com.songify.library.bottomnavigation.SongifyBottomNavigation
import com.songify.library.session.SongifySession
import com.songify.library.theme.SongifyTheme
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val CLIENT_ID = "9ec02e7f10514c15842363d73f64f985"

@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    @Inject
    lateinit var circuit: Circuit

    @Inject
    lateinit var songifySession: SongifySession

    @Inject
    lateinit var startScreen: Screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        login()
    }

    private fun login() {
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult: ActivityResult ->
            // Check if result comes from the correct activity
            if (activityResult.resultCode == RESULT_OK && activityResult.data != null) {
                val resultCode = activityResult.resultCode
                val response = AuthorizationClient.getResponse(resultCode, activityResult.data)
                when (response.type) {
                    AuthorizationResponse.Type.TOKEN -> setAndGo(response.accessToken)
                    AuthorizationResponse.Type.ERROR -> println("Error")
                    else -> println("Auth flow canceled")

                }
            } else {
                println("No result returned")
            }
        }.launch(createLoginActivityIntent())
    }

    private fun createLoginActivityIntent(): Intent {
        val request = AuthorizationRequest.Builder(
            CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            "songify://callback"
        )
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-email"))
            .setCampaign("your-campaign-token")
            .build()

        return AuthorizationClient.createLoginActivityIntent(this, request)
    }

    private fun setAndGo(accessToken: String) {
        songifySession.accessToken = accessToken

        setContent {
            ShowSpotify()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun ShowSpotify(
        modifier: Modifier = Modifier,
    ) {
        val backStack = rememberSaveableBackStack(startScreen)
        val navigator = rememberCircuitNavigator(backStack)

        SongifyTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                Scaffold(
                    bottomBar = { SongifyBottomNavigation({ navigator.resetRoot(it) }) }
                ) {
                    CircuitCompositionLocals(circuit) {
                        NavigableCircuitContent(navigator, backStack)
                    }
                }
            }
        }
    }
}
