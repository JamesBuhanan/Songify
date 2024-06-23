package com.songify.feature.detail.internal.ui

import androidx.compose.ui.Modifier

internal fun Modifier.conditional(
    condition: Boolean,
    block: Modifier.() -> Modifier
): Modifier = if (condition) this.then(block()) else this
