package com.songify.feature.detail.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.songify.feature.detail.R
import com.songify.library.design.AsyncImageWithPlaceholder

enum class ListItemCardType { ALBUM, ARTIST, TRACK, PLAYLIST }

@Composable
internal fun CompactListItemCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    trailingButtonIcon: ImageVector,
    onTrailingButtonIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
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
            .clickable { onClick() }
            .then(modifier),
        shape = shape,
        elevation = CardDefaults.outlinedCardElevation()
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
                        .aspectRatio(1f, true)
                        .conditional(thumbnailShape != null) { clip(thumbnailShape!!) },
                    model = it,
                    contentScale = ContentScale.Crop,
                    isLoadingPlaceholderVisible = isLoadingPlaceHolderVisible,
                    onImageLoading = { onThumbnailLoading?.invoke() },
                    onImageLoadingFinished = { onThumbnailImageLoadingFinished?.invoke(it) },
                    placeholderHighlight = placeholderHighlight,
                    errorPainter = errorPainter,
                    alpha = LocalContentColor.current.alpha,
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

@Composable
internal fun CompactListItemCard(
    cardType: ListItemCardType,
    title: String,
    subtitle: String,
    thumbnailImageUrlString: String?,
    onClick: () -> Unit,
    onTrailingButtonIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
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
    CompactListItemCard(
        modifier = modifier,
        backgroundColor = backgroundColor,
        shape = shape,
        thumbnailImageUrlString = thumbnailImageUrlString,
        title = title,
        subtitle = subtitle,
        onClick = onClick,
        trailingButtonIcon = if (cardType == ListItemCardType.TRACK) {
            Icons.Filled.MoreVert
        } else {
            ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_right_24)
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
