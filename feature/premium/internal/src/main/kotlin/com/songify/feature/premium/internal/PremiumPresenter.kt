package com.songify.feature.premium.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.songify.common.di.AppScope
import com.songify.feature.premium.PremiumScreen
import com.songify.library.premium.usecase.GetPremiumPlans
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PremiumPresenter @AssistedInject constructor(
    private val getPremiumPlans: GetPremiumPlans,
    @Assisted private val navigator: Navigator,
) : Presenter<PremiumState> {
    @Composable
    override fun present(): PremiumState {
        val state by produceState<PremiumState>(PremiumState.Loading) {
            value = PremiumState.Success(
                premiumPlans = getPremiumPlans(),
                eventSink = {
                    when (it) {
                        is PremiumEvent.TappedBack -> navigator.pop()
                        is PremiumEvent.TappedPremiumPlan -> {}
                    }
                }
            )
        }

        return state
    }

    @CircuitInject(PremiumScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): PremiumPresenter
    }
}
