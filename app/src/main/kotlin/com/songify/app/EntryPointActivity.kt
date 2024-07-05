package com.songify.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import app.cash.better.dynamic.features.ExperimentalDynamicFeaturesApi
import app.cash.better.dynamic.features.dynamicImplementations
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import com.songify.app.api.CircuitFeature
import com.songify.app.install.SplitInstallHelper
import com.songify.app.install.rememberSplitInstallHelper
import com.songify.feature.login.LoginScreen
import com.songify.library.bottomnavigation.SongifyBottomNavigation
import com.songify.library.theme.SongifyTheme
import kotlinx.coroutines.CoroutineScope

class EntryPointActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent { ShowSpotify() }
    }

    @OptIn(ExperimentalDynamicFeaturesApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun ShowSpotify(
        modifier: Modifier = Modifier,
    ) {
        val installHelper = rememberSplitInstallHelper()

        val features by remember {
            installHelper.splitInstallManager.dynamicImplementations<CircuitFeature>()
        }.collectAsState(initial = emptyList())

        val isSplitInstalling by installHelper.isInstalling.collectAsState(initial = false)

        val circuit = Circuit.Builder()
            .apply {
                features.forEach {
                    it.injectFeature()

                    for (factory in it.presenterFactories()) {
                        addPresenterFactory(factory)
                    }
                    for (factory in it.uiFactories()) {
                        addUiFactory(factory)
                    }
                }
            }
            .build()

        val backStack = rememberSaveableBackStack(LoginScreen)
        val navigator = rememberDynamicNavigator(
            delegate = rememberCircuitNavigator(backStack),
            scope = rememberCoroutineScope(),
            splitInstallHelper = rememberSplitInstallHelper(),
        )

        SongifyTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                val splitInstallHelper = rememberSplitInstallHelper()
                val scope = rememberCoroutineScope()
                Scaffold(
                    bottomBar = {
                        SongifyBottomNavigation({
                            splitInstallHelper.requestInstall(it, scope) {
                                navigator.resetRoot(it)
                            }
                        })
                    }
                ) {
                    CircuitCompositionLocals(circuit) {
                        NavigableCircuitContent(navigator, backStack)
                    }
                }
            }
        }
    }
}

@Composable
internal fun rememberDynamicNavigator(
    delegate: Navigator,
    scope: CoroutineScope,
    splitInstallHelper: SplitInstallHelper,
): Navigator {
    return remember {
        DynamicNavigator(
            delegate = delegate,
            scope = scope,
            splitInstallHelper = splitInstallHelper,
        )
    }
}

internal class DynamicNavigator(
    private val delegate: Navigator,
    private val scope: CoroutineScope,
    private val splitInstallHelper: SplitInstallHelper
) : Navigator by delegate {
    override fun goTo(screen: Screen): Boolean {
        splitInstallHelper.requestInstall(screen, scope) {
            delegate.goTo(screen)
        }

        return true
    }
}
