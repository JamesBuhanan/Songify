package com.songify.feature.login.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.songify.feature.login.LoginScreen
import com.songify.library.loading.LoadingBar
import dagger.hilt.components.SingletonComponent

@CircuitInject(LoginScreen::class, SingletonComponent::class)
@Composable
fun LoginView(
    state: LoginState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is LoginState.Loading -> LoadingBar()
    }
}
