package com.songify.library.posts

import com.squareup.moshi.Json

enum class RegistrationMode {
    @Json(name = "Closed")
    Closed,

    @Json(name = "RequireApplication")
    RequireApplication,

    @Json(name = "Open")
    Open,
}

/**
 * Different sort types used in lemmy.
 */
enum class SortType {
    /**
     * Posts sorted by the most recent comment.
     */
    @Json(name = "Active")
    Active,

    /**
     * Posts sorted by the published time.
     */
    @Json(name = "Hot")
    Hot,

    @Json(name = "New")
    New,

    /**
     * Posts sorted by the published time ascending
     */
    @Json(name = "Old")
    Old,

    /**
     * The top posts for this last day.
     */
    @Json(name = "TopDay")
    TopDay,

    /**
     * The top posts for this last week.
     */
    @Json(name = "TopWeek")
    TopWeek,

    /**
     * The top posts for this last month.
     */
    @Json(name = "TopMonth")
    TopMonth,

    /**
     * The top posts for this last year.
     */
    @Json(name = "TopYear")
    TopYear,

    /**
     * The top posts of all time.
     */
    @Json(name = "TopAll")
    TopAll,

    /**
     * Posts sorted by the most comments.
     */
    @Json(name = "MostComments")
    MostComments,

    /**
     * Posts sorted by the newest comments, with no necrobumping. IE a forum sort.
     */
    @Json(name = "NewComments")
    NewComments,

    /**
     * Posts sorted by the top hour.
     */
    @Json(name = "TopHour")
    TopHour,

    /**
     * Posts sorted by the top six hour.
     */
    @Json(name = "TopSixHour")
    TopSixHour,

    /**
     * Posts sorted by the top twelve hour.
     */
    @Json(name = "TopTwelveHour")
    TopTwelveHour,

    /**
     * Posts sorted by the top three months.
     */
    @Json(name = "TopThreeMonths")
    TopThreeMonths,

    /**
     * Posts sorted by the top six months.
     */
    @Json(name = "TopSixMonths")
    TopSixMonths,

    /**
     * Posts sorted by the top nine months.
     */
    @Json(name = "TopNineMonths")
    TopNineMonths,
}
// When updating this enum, don't forget to update MAP_SORT_TYPE_SHORT_FORM and MAP_SORT_TYPE_LONG_FORM

/**
 * Different comment sort types used in lemmy.
 */
enum class CommentSortType {
    /**
     * Comments sorted by a decaying rank.
     */
    @Json(name = "Hot")
    Hot,

    /**
     * Comments sorted by top score.
     */
    @Json(name = "Top")
    Top,

    /**
     * Comments sorted by new.
     */
    @Json(name = "New")
    New,

    /**
     * Comments sorted by old.
     */
    @Json(name = "Old")
    Old,
}

/**
 * The different listing types for post and comment fetches.
 */
enum class ListingType {

    @Json(name = "All")
    All,

    @Json(name = "Local")
    Local,

    @Json(name = "Subscribed")
    Subscribed,
}

/**
 * Different Subscribed states
 */
enum class SubscribedType {
    @Json(name = "Subscribed")
    Subscribed,

    @Json(name = "NotSubscribed")
    NotSubscribed,

    @Json(name = "Pending")
    Pending,
}
