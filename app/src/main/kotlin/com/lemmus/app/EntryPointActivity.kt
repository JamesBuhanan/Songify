package com.lemmus.app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lemmus.common.di.ActivityKey
import com.lemmus.common.di.AppScope
import com.lemmus.common.theme.LemmusTheme
import com.lemmus.feature.splash.SplashScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

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

            LemmusTheme {
                CircuitCompositionLocals(circuit) {
                    NavigableCircuitContent(navigator, backStack)
                }
            }
        }
    }
}
