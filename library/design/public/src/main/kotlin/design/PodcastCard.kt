package design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * A card that shows basic information about a particular podcast.
 * Note: The width of the composable is fixed at 175dp.
 *
 * @param podcastArtUrlString the url of the podcast art.
 * @param name the name of the podcast.
 * @param nameOfPublisher the name of the publisher of the podcast.
 * @param onClick the lambda to execute when the item is clicked.
 * @param modifier the modifier to be applied to the composable. The
 * width is fixed at 160dp.
 */
@Composable
fun PodcastCard(
    podcastArtUrlString: String,
    name: String,
    nameOfPublisher: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isLoadingPlaceholderVisible by remember { mutableStateOf(true) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .width(160.dp)
            .height(IntrinsicSize.Min)
            .clickable { onClick() }
            .then(modifier),
        shape = RectangleShape,
        elevation = CardDefaults.outlinedCardElevation()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImageWithPlaceholder(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = podcastArtUrlString,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                isLoadingPlaceholderVisible = isLoadingPlaceholderVisible,
                onImageLoading = { isLoadingPlaceholderVisible = true },
                onImageLoadingFinished = { isLoadingPlaceholderVisible = false }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = nameOfPublisher,
                style = MaterialTheme.typography.titleSmall.copy(
                    Color.White.copy(alpha = .66f)
                ),
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}