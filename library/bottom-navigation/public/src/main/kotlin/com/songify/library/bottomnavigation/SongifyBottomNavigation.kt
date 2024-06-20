package com.songify.library.bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.slack.circuit.runtime.screen.Screen

object SongifyBottomNavigationConstants {
    val navigationHeight = 60.dp
}

/**
 * A bottom navigation bar composable with a background gradient.
 * Note: The bottom navigation bar has a fixed height of 80dp.
 *
 * @param navigationItems the [BottomNavItem] to
 * display in the navigation bar.
 * @param currentlySelectedItem the currently selected [BottomNavItem].
 * The currently selected item will be highlighted and will also use the
 * [BottomNavItem.filledIconVariantResourceId] for the image resource.
 * @param onItemClick the lambda to execute when an item is clicked. A reference to
 * an instance of [BottomNavItem] that was clicked will be provided
 * as a parameter to the lambda.
 * @param modifier the modifier to be applied to the navigation bar. The height of the
 * composable is fixed at 80dp.
 * @see [com.example.musify.ui.navigation.MusifyBottomNavigationConnectedWithBackStack]
 */
@Composable
fun SongifyBottomNavigation(
    onClick: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    val gradientBrush = remember {
        Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Black,
                0.3f to Color.Black.copy(alpha = 0.9f),
                0.5f to Color.Black.copy(alpha = 0.8f),
                0.7f to Color.Black.copy(alpha = 0.6f),
                0.9f to Color.Black.copy(alpha = 0.2f),
                1f to Color.Transparent
            ),
            startY = Float.POSITIVE_INFINITY,
            endY = 0.0f
        )
    }
    var currentlySelectedItem: BottomNavItem by remember {
        mutableStateOf(BottomNavItem.Home)
    }

    // use surface because the default height of bottom navigation
    // according to the material spec is lower than the height
    // used by spotify for it's navigation bar
    Surface(
        modifier = Modifier
            .background(gradientBrush)
            .then(modifier),
        color = Color.Transparent,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(SongifyBottomNavigationConstants.navigationHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NavigationBar {
                bottomNavItems.forEach {
                    NavigationBarItem(
                        selected = it == currentlySelectedItem,
                        onClick = {
                            currentlySelectedItem = it
                            onClick(it.screen)
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    if (it == currentlySelectedItem) {
                                        it.filledIconVariantResourceId
                                    } else {
                                        it.outlinedIconVariantResourceId
                                    }
                                ),
                                contentDescription = null
                            )
                        },
                        label = { Text(text = it.label) }
                    )
                }
            }
        }
    }
}
