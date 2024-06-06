package com.songify.library.spotify.model


sealed class CarouselCard {
    abstract val id: String
    abstract val imageUrlString: String?
    abstract val caption: String

    /**
     * Note: The [artistsString] property is meant to hold a comma separated
     * list of artists who worked on the album.
     */
    data class AlbumCard(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val name: String,
        val artistsString: String,
        val yearOfReleaseString: String,
    ) : CarouselCard()

    data class ArtistCard(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
    ) : CarouselCard()

    data class PlaylistCard(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val name: String,
        val ownerName: String,
        val totalNumberOfTracks: String,
    ) : CarouselCard()

    /**
     * Note: The [artistsString] property is meant to hold a comma separated
     * list of artists who worked on the track.
     */
    data class TrackCard(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val name: String,
        val artistsString: String,
        val trackUrlString: String?,
    ) : CarouselCard(), Streamable {
        override val streamInfo = StreamInfo(
            streamUrl = trackUrlString,
            imageUrl = imageUrlString,
            title = name,
            subtitle = artistsString
        )
    }

    data class PodcastCard(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val name: String,
        val nameOfPublisher: String,
    ) : CarouselCard()

    data class EpisodeCard(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val episodeContentInfo: EpisodeContentInfo,
        val episodeReleaseDateInfo: EpisodeReleaseDateInfo,
        val episodeDurationInfo: EpisodeDurationInfo
    ) : CarouselCard() {
        data class EpisodeContentInfo(
            val title: String,
            val description: String,
            val imageUrlString: String
        )

        data class EpisodeReleaseDateInfo(val month: String, val day: Int, val year: Int)
        data class EpisodeDurationInfo(val hours: Int, val minutes: Int)
    }
}
