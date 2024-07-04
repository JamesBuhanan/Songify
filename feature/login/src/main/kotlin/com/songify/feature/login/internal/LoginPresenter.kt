package com.songify.feature.login.internal

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Debug
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.feature.home.HomeScreen
import com.songify.feature.login.LoginScreen
import com.songify.library.di.FeatureScope
import com.songify.library.session.SongifySession
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class LoginPresenter @AssistedInject constructor(
    private val songifySession: SongifySession,
    @Assisted private val navigator: Navigator,
) : Presenter<LoginState> {
    @Composable
    override fun present(): LoginState {
//        Debug.waitForDebugger()
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult: ActivityResult ->
                // Check if result comes from the correct activity
                if (activityResult.resultCode == RESULT_OK && activityResult.data != null) {
                    val resultCode = activityResult.resultCode
                    val response =
                        AuthorizationClient.getResponse(resultCode, activityResult.data)
                    when (response.type) {
                        AuthorizationResponse.Type.TOKEN -> {
                            songifySession.accessToken = response.accessToken
                            navigator.goTo(HomeScreen)
                        }

                        AuthorizationResponse.Type.ERROR -> {
                            println("Error")
                        }

                        else -> println("Auth flow canceled")

                    }
                } else {
                    println("No result returned")
                }
            }

        val activity = requireNotNull(LocalContext.current.getActivity())
        LaunchedEffect(launcher) {
            launcher.launch(createLoginActivityIntent(activity))
        }

        return LoginState.Loading
    }

    @CircuitInject(LoginScreen::class, FeatureScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): LoginPresenter
    }
}

const val CLIENT_ID = "9ec02e7f10514c15842363d73f64f985"
private fun createLoginActivityIntent(activity: Activity): Intent {
    val request = AuthorizationRequest.Builder(
        CLIENT_ID,
        AuthorizationResponse.Type.TOKEN,
        "songify://callback"
    )
        .setShowDialog(false)
        .setScopes(arrayOf("user-read-email"))
        .setCampaign("your-campaign-token")
        .build()

    return AuthorizationClient.createLoginActivityIntent(
        activity,
        request
    )
}

private fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}
