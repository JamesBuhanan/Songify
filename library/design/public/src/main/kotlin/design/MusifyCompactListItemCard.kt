package design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.songify.library.design.R

/**
 * An enum containing the all the types of list item cards.
 */
enum class ListItemCardType { ALBUM, ARTIST, TRACK, PLAYLIST }

/**
 * A composable that represents a compact list item. This composable
 * contains an argument for setting the [trailingButtonIcon]. To
 * automatically set the trailing icon and the shape of the thumbnail
 * based on the [ListItemCardType], use the other overload. The composable
 * will ensure that it has a minimum height of 56.dp and a minimum width
 * of 250.dp. Size values below the minimum values will be ignored.
 * The maximum height of the composable can be of 80.dp. Any values
 * higher than 80.dp will be ignored, and the size would be set to 80.dp.
 * Also, note that the alpha value of the thumbnail image is set to
 * the current value of [LocalContentAlpha].
 *
 * @param thumbnailImageUrlString the url of the image to use as the
 * thumbnail. If this is null, only the text with subtitle will be
 * displayed.
 * @param title the title of the card.
 * @param subtitle the subtitle of the card.
 * @param onClick the callback to execute when the card is clicked.
 * @param trailingButtonIcon an instance of [ImageVector] that will be used
 * as the trailing icon.
 * @param onTrailingButtonIconClick the callback to execute when the
 * [trailingButtonIcon] is clicked.
 * @param modifier the modifier to be applied to the card.
 * @param backgroundColor used to specify the background color of the card.
 * @param shape used to specify the shape of the card.
 * @param thumbnailShape the shape of the thumbnail image. If it is
 * not set, a square shape will be used.
 * @param titleTextStyle the style configuration for the [title] such as
 * color, font, line height etc.
 * @param subtitleTextStyle the style configuration for the [subtitle] such
 * as color, font, line height etc.
 * @param onThumbnailLoading the callback to execute when the thumbnail
 * image is loading.
 * @param onThumbnailImageLoadingFinished the lambda to execute when the image
 * is done loading. A nullable parameter of type [Throwable] is provided
 * to the lambda, that indicates whether the image loading process was
 * successful or not.
 * @param errorPainter A [Painter] that is displayed when the image request is unsuccessful.
 * @param isLoadingPlaceHolderVisible indicates whether the loading
 * placeholder is visible for the thumbnail image.
 * @param contentPadding the [PaddingValues] to be applied to the content
 * of the card.
 * @param placeholderHighlight the [PlaceholderHighlight] to apply to the
 * placeholder that is displayed when the thumbnail image is loading.
 */
@Composable
fun MusifyCompactListItemCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    trailingButtonIcon: ImageVector,
    onTrailingButtonIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shape: Shape = MaterialTheme.shapes.medium,
    thumbnailImageUrlString: String? = null,
    thumbnailShape: Shape? = null,
    titleTextStyle: TextStyle = LocalTextStyle.current,
    subtitleTextStyle: TextStyle = LocalTextStyle.current,
    isLoadingPlaceHolderVisible: Boolean = false,
    onThumbnailLoading: (() -> Unit)? = null,
    onThumbnailImageLoadingFinished: ((Throwable?) -> Unit)? = null,
    errorPainter: Painter? = null,
    placeholderHighlight: PlaceholderHighlight = PlaceholderHighlight.shimmer(),
    contentPadding: PaddingValues = PaddingValues(all = 8.dp)
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        modifier = Modifier
            .sizeIn(minHeight = 56.dp, minWidth = 250.dp, maxHeight = 80.dp)
            .then(modifier)
            .clickable { onClick() },
        shape = shape,
        elevation = CardDefaults.outlinedCardElevation(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            thumbnailImageUrlString?.let {
                AsyncImageWithPlaceholder(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f, true),
//                        .conditional(thumbnailShape != null) { clip(thumbnailShape!!) },
                    model = it,
                    contentScale = ContentScale.Crop,
                    isLoadingPlaceholderVisible = isLoadingPlaceHolderVisible,
                    onImageLoading = { onThumbnailLoading?.invoke() },
                    onImageLoadingFinished = { onThumbnailImageLoadingFinished?.invoke(it) },
                    placeholderHighlight = placeholderHighlight,
                    errorPainter = errorPainter,
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = titleTextStyle
                )
                Text(
                    text = subtitle,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = subtitleTextStyle
                )
            }
            IconButton(
                onClick = onTrailingButtonIconClick
            ) {
                Icon(
                    imageVector = trailingButtonIcon,
                    contentDescription = null
                )
            }
        }
    }
}


/**
 * A composable that represents a compact list item. This overload will
 * ensure the use of correct trailing icon and thumbnail shape based
 * on the [cardType]. The composable has predefined size values.
 * Size values below/above the minimum/maximum values will be ignored.
 * See the other overload to know the minimum/maximum size values.
 * If a specific trailing icon is needed, use the other overload.
 * Note that the alpha value of the thumbnail image is set to
 * the current value of [LocalContentAlpha].
 *
 * @param thumbnailImageUrlString the url of the image to use as the
 * thumbnail.
 * @param title the title of the card.
 * @param subtitle the subtitle of the card.
 * @param onClick the callback to execute when the card is clicked.
 * @param onTrailingButtonIconClick the callback to execute when the trailingButtonIcon
 * is clicked.
 * @param modifier the modifier to be applied to the card.
 * @param backgroundColor used to specify the background color of the card.
 * @param shape used to specify the shape of the card.
 * @param titleTextStyle The style configuration for the [title] such as
 * color, font, line height etc.
 * @param subtitleTextStyle The style configuration for the [subtitle] such
 * as color, font, line height etc.
 * @param onThumbnailLoading the callback to execute when the thumbnail
 * image is loading.
 * @param onThumbnailImageLoadingFinished the lambda to execute when the image
 * is done loading. A nullable parameter of type [Throwable] is provided
 * to the lambda, that indicates whether the image loading process was
 * successful or not.
 * @param errorPainter A [Painter] that is displayed when the image request is unsuccessful.
 * @param isLoadingPlaceHolderVisible indicates whether the loading
 * placeholder is visible for the thumbnail image.
 * @param contentPadding the [PaddingValues] to be applied to the content
 * of the card.
 * @param placeholderHighlight the [PlaceholderHighlight] to apply to the
 * placeholder that is displayed when the thumbnail image is loading.
 */
@Composable
fun MusifyCompactListItemCard(
    cardType: ListItemCardType,
    title: String,
    subtitle: String,
    thumbnailImageUrlString: String?,
    onClick: () -> Unit,
    onTrailingButtonIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shape: Shape = MaterialTheme.shapes.medium,
    titleTextStyle: TextStyle = LocalTextStyle.current,
    subtitleTextStyle: TextStyle = LocalTextStyle.current,
    isLoadingPlaceHolderVisible: Boolean = false,
    onThumbnailLoading: (() -> Unit)? = null,
    onThumbnailImageLoadingFinished: ((Throwable?) -> Unit)? = null,
    errorPainter: Painter? = null,
    placeholderHighlight: PlaceholderHighlight = PlaceholderHighlight.shimmer(),
    contentPadding: PaddingValues = PaddingValues(8.dp)
) {
    MusifyCompactListItemCard(
        modifier = modifier,
        backgroundColor = backgroundColor,
        shape = shape,
        thumbnailImageUrlString = thumbnailImageUrlString,
        title = title,
        subtitle = subtitle,
        onClick = onClick,
        trailingButtonIcon = when (cardType) {
            ListItemCardType.TRACK -> Icons.Filled.MoreVert
            else -> ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_right_24)
        },
        onTrailingButtonIconClick = onTrailingButtonIconClick,
        thumbnailShape = if (cardType == ListItemCardType.ARTIST) CircleShape else null,
        titleTextStyle = titleTextStyle,
        subtitleTextStyle = subtitleTextStyle,
        isLoadingPlaceHolderVisible = isLoadingPlaceHolderVisible,
        onThumbnailLoading = onThumbnailLoading,
        onThumbnailImageLoadingFinished = onThumbnailImageLoadingFinished,
        placeholderHighlight = placeholderHighlight,
        errorPainter = errorPainter,
        contentPadding = contentPadding
    )
}