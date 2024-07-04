package com.songify.app.install

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.play.core.ktx.requestInstall
import com.google.android.play.core.ktx.status
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.slack.circuit.runtime.screen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@Composable
internal fun rememberSplitInstallHelper(): SplitInstallHelper {
    val context = LocalContext.current
    return remember { SplitInstallHelper(context) }
}

internal class SplitInstallHelper(
    context: Context,
    val splitInstallManager: SplitInstallManager = SplitInstallManagerFactory.create(context),
) {
    val isInstalling: Flow<Boolean> = callbackFlow {
        send(false)
        val listener = SplitInstallStateUpdatedListener {
            when (it.status) {
                SplitInstallSessionStatus.PENDING,
                SplitInstallSessionStatus.DOWNLOADING,
                SplitInstallSessionStatus.DOWNLOADED,
                SplitInstallSessionStatus.INSTALLING,
                -> trySend(true)

                else -> trySend(false)
            }
        }

        splitInstallManager.registerListener(listener)
        awaitClose {
            splitInstallManager.unregisterListener(listener)
        }
    }

//    suspend fun requestUninstall(module: Module) {
//        splitInstallManager.requestDeferredUninstall(moduleNames = listOf(module.id))
//    }

    fun requestInstall(
        screen: Screen,
        scope: CoroutineScope,
        onInstalled: suspend () -> Unit
    ) {
        // Hopefully the screenPrefix maps to the module we are trying to load...
        val moduleId = screen::class.java.simpleName.removeSuffix("Screen").toLowerCase()
        if (splitInstallManager.installedModules.contains(moduleId)) {
            scope.launch { onInstalled() }
            return
        }

        var listener: SplitInstallStateUpdatedListener? = null
        listener = SplitInstallStateUpdatedListener {
            if (it.status == SplitInstallSessionStatus.INSTALLED) {
                scope.launch {
                    delay(200)
                    onInstalled()
                    splitInstallManager.unregisterListener(listener!!)
                }
            }
        }

        splitInstallManager.registerListener(listener)
        scope.launch {
            splitInstallManager.requestInstall(modules = listOf(moduleId))
        }
    }
}
