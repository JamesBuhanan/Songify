package com.songify.feature.home.internal

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.slack.circuit.test.TestEventSink
import com.songify.feature.home.internal.HomeConstants.CARD_TAG
import com.songify.feature.home.internal.HomeConstants.HOME_TAG
import com.songify.feature.home.internal.HomeConstants.ROW_HEADLINE_TAG
import com.songify.feature.home.internal.TestData.NEW_RELEASES
import com.songify.library.loading.PROGRESS_TAG
import org.junit.Rule
import org.junit.Test

class HomeViewTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeView_show_progress_indicator_for_loading_state() {
        composeTestRule.run {
            setContent { HomeView(HomeState.Loading) }

            onNodeWithTag(PROGRESS_TAG).assertIsDisplayed()
            onNodeWithTag(HOME_TAG).assertDoesNotExist()
        }
    }

    @Test
    fun homeView_show_screen_for_success() {
        val testSink = TestEventSink<HomeEvent>()
        val success = HomeState.Success(TestData.homeFeed, testSink)
        composeTestRule.run {
            setContent { HomeView(success) }

            onNodeWithTag(PROGRESS_TAG).assertDoesNotExist()
            onNodeWithTag(HOME_TAG).assertIsDisplayed()
            onNodeWithTag(ROW_HEADLINE_TAG).assertIsDisplayed().assertTextEquals(NEW_RELEASES)
        }
    }

    @Test
    fun homeView_emits_event_when_tapping_on_card() {
        val testSink = TestEventSink<HomeEvent>()
        val success = HomeState.Success(TestData.homeFeed, testSink)
        composeTestRule.run {
            setContent { HomeView(success) }

            onNodeWithTag(PROGRESS_TAG).assertDoesNotExist()
            onNodeWithTag(HOME_TAG).assertIsDisplayed()
            onNodeWithTag(ROW_HEADLINE_TAG).assertIsDisplayed().assertTextEquals(NEW_RELEASES)

            onAllNodesWithTag(CARD_TAG).assertCountEquals(1)[0].performClick()

            testSink.assertEvent(HomeEvent.TappedCard(TestData.album))
        }
    }
}
