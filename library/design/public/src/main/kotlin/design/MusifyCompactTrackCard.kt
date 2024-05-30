//package design
//
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.material3.LocalTextStyle
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import com.example.musify.domain.SearchResult
//
///**
// * A compact card that is used to display the information of a particular
// * track. This is a wrapper over [com.example.musify.ui.components.MusifyCompactListItemCard].
// * This composable is mainly responsible for providing two
// * important functionalities:
// * 1) Setting the content alpha based on whether the track is available.
// * 2) Setting the text style of the composable based on whether it is
// * playing or not.
// *
// * @param track an instance of [SearchResult.TrackSearchResult] that the card
// * is based upon.
// * @param onClick the callback to execute when the card is clicked.
// * @param isLoadingPlaceholderVisible indicates whether the loading
// * placeholder is visible for the thumbnail image.
// * @param modifier [Modifier] to be applied to the card.
// * @param isCurrentlyPlaying indicates whether the [track] is the currently
// * playing track, based on which, the style of the card will be set.
// * @param isAlbumArtVisible indicates whether the album art is visible
// * or not.
// * @param onImageLoading the callback to execute when the image associated
// * with [SearchResult.TrackSearchResult.imageUrlString] is loading.
// * image is loading.
// * @param onImageLoadingFinished the lambda to execute when the image
// * associated with [SearchResult.TrackSearchResult.imageUrlString] is
// * is done loading. A nullable parameter of type [Throwable] is provided
// * to the lambda, that indicates whether the image loading process was
// * @param titleTextStyle The style configuration for the title of the
// * track.
// * @param subtitleTextStyle The style configuration for the subtitle of
// * the track.
// */
//@Composable
//fun MusifyCompactTrackCard(
//    track: SearchResult.TrackSearchResult,
//    onClick: (SearchResult.TrackSearchResult) -> Unit,
//    isLoadingPlaceholderVisible: Boolean,
//    modifier: Modifier = Modifier,
//    shape: Shape = RectangleShape,
//    backgroundColor: Color = MaterialTheme.colors.surface,
//    isCurrentlyPlaying: Boolean = false,
//    isAlbumArtVisible: Boolean = true,
//    onImageLoading: ((SearchResult.TrackSearchResult) -> Unit)? = null,
//    onImageLoadingFinished: ((SearchResult.TrackSearchResult, Throwable?) -> Unit)? = null,
//    titleTextStyle: TextStyle = LocalTextStyle.current,
//    subtitleTextStyle: TextStyle = LocalTextStyle.current,
//    contentPadding: PaddingValues = MusifyCompactTrackCardDefaults.defaultContentPadding
//) {
//    val trackPlayingTextStyle = LocalTextStyle.current.copy(
//        color = MaterialTheme.colors.primary
//    )
//    // set alpha based on whether the track is available for playback
//    MusifyCompactListItemCard(
//        modifier = modifier,
//        backgroundColor = backgroundColor,
//        shape = shape,
//        cardType = ListItemCardType.TRACK,
//        thumbnailImageUrlString = if (isAlbumArtVisible) track.imageUrlString else null,
//        title = track.name,
//        subtitle = track.artistsString,
//        onClick = { onClick(track) },
//        onTrailingButtonIconClick = {},
//        isLoadingPlaceHolderVisible = isLoadingPlaceholderVisible,
//        onThumbnailLoading = { onImageLoading?.invoke(track) },
//        onThumbnailImageLoadingFinished = { throwable ->
//            onImageLoadingFinished?.invoke(track, throwable)
//        },
//        titleTextStyle = if (isCurrentlyPlaying) trackPlayingTextStyle else titleTextStyle,
//        subtitleTextStyle = subtitleTextStyle,
//        contentPadding = contentPadding
//    )
//}
//
///**
// * Contains default values used by [MusifyCompactTrackCard].
// */
//object MusifyCompactTrackCardDefaults {
//    val defaultContentPadding = PaddingValues(
//        horizontal = 16.dp,
//        vertical = 8.dp
//    )
//}