package com.songify.feature.detail.internal.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.songify.feature.detail.internal.R
import com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier.DynamicBackgroundResource
import com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier.DynamicBackgroundStyle
import com.songify.library.design.dynamicTheme.dynamicbackgroundmodifier.dynamicBackground
import com.songify.library.theme.SongifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailScreenTopAppBar(
    title: String,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    dynamicBackgroundResource: DynamicBackgroundResource = DynamicBackgroundResource.Empty
) {
    val dynamicBackgroundStyle = remember {
        DynamicBackgroundStyle.Filled(scrimColor = Color.Black.copy(alpha = 0.3f))
    }

    // Since the top app bar's background color is transparent,
    // any elevation to the app bar would make it look like it has
    // a border. Therefore, set the elevation to 0dp.
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .dynamicBackground(dynamicBackgroundResource, dynamicBackgroundStyle)
            .then(modifier)
            .clickable(onClick = onClick),
        title = {
            Center {
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .offset(y = 1.dp),
                    onClick = onBackButtonClicked
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_left_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}

@Preview
@Composable
internal fun DetailScreenTopAppBarPreview() {
    SongifyTheme {
        DetailScreenTopAppBar(
            title = "Title",
            onBackButtonClicked = {},
            dynamicBackgroundResource = DynamicBackgroundResource.Empty
        )
    }
}

@Composable
internal fun Center(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
