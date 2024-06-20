package com.songify.feature.home.internal

import com.google.common.truth.Truth.assertThat
import com.slack.circuit.test.FakeNavigator
import com.slack.circuit.test.test
import com.songify.feature.home.HomeScreen
import com.songify.feature.home.sharedtest.FakeGetHomeFeed
import com.songify.feature.home.sharedtest.TestData
import com.songify.feature.premium.DetailScreen
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomePresenterTest {
    private val fakeNavigator = FakeNavigator(HomeScreen)
    private val presenter = HomePresenter(
        getHomeFeed = FakeGetHomeFeed(),
        navigator = fakeNavigator,
    )

    @Test
    fun `data loads and then clicking item navigates to details screen`() = runTest {
        presenter.test {
            // Starts off Loading
            assertThat(awaitItem()).isEqualTo(HomeState.Loading)

            // HomeFeed returns Success
            val successState = awaitItem() as HomeState.Success
            assertThat(successState.homeFeed).isEqualTo(TestData.homeFeed)

            // Tap an Album go to DetailScreen
            successState.eventSink(HomeEvent.TappedCard(TestData.album))
            assertThat(fakeNavigator.awaitNextScreen()).isEqualTo(DetailScreen(TestData.album))
        }
    }
}
