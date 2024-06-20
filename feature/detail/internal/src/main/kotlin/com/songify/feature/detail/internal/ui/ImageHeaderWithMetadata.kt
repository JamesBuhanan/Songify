package com.songify.feature.detail.internal.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.songify.feature.detail.internal.R
import com.songify.library.design.AsyncImageWithPlaceholder

sealed class HeaderImageSource {
    data class ImageFromUrlString(val urlString: String) : HeaderImageSource()
    data class ImageFromDrawableResource(@DrawableRes val resourceId: Int) : HeaderImageSource()
}

@Composable
fun ImageHeaderWithMetadata(
    title: String,
    headerImageSource: HeaderImageSource,
    subtitle: String,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isLoadingPlaceholderVisible: Boolean = false,
    onImageLoading: () -> Unit = {},
    onImageLoaded: (Throwable?) -> Unit = {},
    additionalMetadataContent: @Composable () -> Unit,
) {
    Box(modifier) {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .padding(8.dp),
            onClick = onBackButtonClicked
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_left_24),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (headerImageSource) {
                is HeaderImageSource.ImageFromUrlString -> {
                    AsyncImageWithPlaceholder(
                        modifier = Modifier
                            .size(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .shadow(8.dp),
                        model = headerImageSource.urlString,
                        contentDescription = null,
                        isLoadingPlaceholderVisible = isLoadingPlaceholderVisible,
                        onImageLoading = onImageLoading,
                        onImageLoadingFinished = onImageLoaded,
                        contentScale = ContentScale.Crop
                    )
                }

                is HeaderImageSource.ImageFromDrawableResource -> {
                    Image(
                        painter = painterResource(id = headerImageSource.resourceId),
                        modifier = Modifier
                            .size(250.dp)
                            .align(Alignment.CenterHorizontally)
                            .shadow(8.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = subtitle,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
            additionalMetadataContent()
        }
    }
}
