package design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.songify.common.theme.SongifyTheme
import com.songify.library.design.R

/**
 * An appbar that is meant to be used in a detail screen. It is mainly
 * used to display the [title] with a back button. This overload
 * uses the [Modifier.dynamicBackground] modifier.
 *
 * @param title the title to be displayed.
 * @param onBackButtonClicked the lambda to execute with the user clicks
 * on the back button.
 * @param modifier the modifier to be applied to the app bar.
 * @param onClick the lambda to execute when the app bar clicked. This is
 * usually used to scroll a list to the first item.
 * @param dynamicBackgroundResource the resource from which the background
 * color would be extracted from. By default, it is set to [DynamicBackgroundResource.Empty].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopAppBar(
    title: String,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
//                    .align(Alignment.CenterVertically)
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
                modifier = Modifier,
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        },
        modifier = Modifier
            .then(modifier)
            .clickable(onClick = onClick)
            .background(Color.Transparent),
    )
}

@Preview
@Composable
fun DetailScreenTopAppBarPreview() {
    SongifyTheme {
        DetailScreenTopAppBar(
            title = "Title",
            onBackButtonClicked = {},
        )
    }
}