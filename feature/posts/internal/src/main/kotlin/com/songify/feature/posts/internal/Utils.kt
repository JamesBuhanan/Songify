package com.songify.feature.posts.internal

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun pictrsImageThumbnail(src: String, thumbnailSize: Int): String {
    // sample url:
    // http://localhost:8535/pictrs/image/file.png?thumbnail=256&format=jpg

    val split = src.split("/pictrs/image/")

    // If theres not multiple, then its not a pictrs image
    if (split.size == 1) {
        return src
    }

    val host = split[0]
    var path = split[1]
    // eliminate the query param portion of the path so we can replace it later
    // without this, we'd end up with something like host/path?thumbnail=...?thumbnail=...
    if ("?" in path) {
        path = path.replaceAfter('?', "").dropLast(1)
    }

    return "$host/pictrs/image/$path?thumbnail=$thumbnailSize&format=webp"
}

fun isImage(url: String?): Boolean {
    url ?: return false

    return imageRegex.matches(url)
}

val imageRegex = Regex(
    pattern = "(http)?s?:?(//[^\"']*\\.(?:jpg|jpeg|gif|png|svg|webp))",
)

fun covertTimeToText(dataDate: String?): String {
    var convTime: String? = null

    try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")

        val pasTime: Date = dateFormat.parse(dataDate)
        val nowTime = Date()
        val dateDiff: Long = nowTime.time - pasTime.time
        val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
        val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
        val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
        val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
        if (second < 60) {
            convTime = "${second}s"
        } else if (minute < 60) {
            convTime = "${minute}m"
        } else if (hour < 24) {
            convTime = "${hour}h"
        } else if (day >= 7) {
            convTime = if (day > 360) {
                (day / 360).toString() + "y"
            } else if (day > 30) {
                (day / 30).toString() + "M"
            } else {
                (day / 7).toString() + "w"
            }
        } else if (day < 7) {
            convTime = "${day}d"
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return convTime!!
}