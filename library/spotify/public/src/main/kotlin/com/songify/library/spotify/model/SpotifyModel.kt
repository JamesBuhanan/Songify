package com.songify.library.spotify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class SpotifyModel : Parcelable {
    abstract val id: String
    abstract val imageUrlString: String?
    abstract val caption: String

    /**
     * Note: The [artistsString] property is meant to hold a comma separated
     * list of artists who worked on the album.
     */
    data class Album(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val artistsString: String,
        val yearOfRelease: String,
    ) : SpotifyModel(), Parcelable

    data class Artist(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
    ) : SpotifyModel()

    data class Playlist(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val name: String,
        val ownerName: String,
        val totalNumberOfTracks: String,
    ) : SpotifyModel()

    /**
     * Note: The [artistsString] property is meant to hold a comma separated
     * list of artists who worked on the track.
     */
    data class Track(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String,
        val artistsString: String,
        val trackUrlString: String,
    ) : SpotifyModel(), Streamable {
        override val streamInfo = StreamInfo(
            streamUrl = trackUrlString,
            imageUrl = imageUrlString,
            title = caption,
            subtitle = artistsString
        )
    }

    data class Podcast(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val name: String,
        val nameOfPublisher: String,
    ) : SpotifyModel()

    data class Episode(
        override val id: String,
        override val caption: String,
        override val imageUrlString: String?,
        val episodeContent: EpisodeContent,
        val episodeReleaseDate: EpisodeReleaseDate,
        val episodeDuration: EpisodeDuration
    ) : SpotifyModel() {
        @Parcelize
        data class EpisodeContent(
            val title: String,
            val description: String,
            val imageUrlString: String
        ) : Parcelable

        @Parcelize
        data class EpisodeReleaseDate(
            val month: String,
            val day: Int,
            val year: Int,
        ) : Parcelable

        @Parcelize
        data class EpisodeDuration(val hours: Int, val minutes: Int) : Parcelable
    }
}
