package com.songify.feature.posts.internal.common

import android.content.Context
import android.os.Build
import coil.request.ImageRequest
import com.songify.feature.posts.internal.pictrsImageThumbnail

fun getImageRequest(
    context: Context,
    path: String,
    size: Int,
    blur: Boolean,
): ImageRequest {
    val builder = ImageRequest.Builder(context)
        .data(pictrsImageThumbnail(path, size))
        .crossfade(true)

    if (blur && Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
//        builder.transformations(
//            listOf(
//                BlurTransformation(
//                    scale = 0.5f,
//                    radius = 100,
//                ),
//            ),
//        )
    }

    return builder.build()
}
