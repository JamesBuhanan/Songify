package com.songify.library.spotify.model


/**
 * A domain class that contain the [title] and [cards]
 * of a single home feed carousel.
 * @param id the unique id of the car
 */
data class HomeFeedCarousel(
    val id: String,
    val title: String,
    val cards: List<CarouselCard>
)
