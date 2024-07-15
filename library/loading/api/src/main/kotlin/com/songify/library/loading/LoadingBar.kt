package com.songify.library.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewLoadingBar() {
    LoadingBar()
}

const val PROGRESS_TAG = "progress"

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.testTag(PROGRESS_TAG),
        )
    }
}

@Composable
fun InstallingDfmBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Installing DFM...",
            color = Color.Red,
        )

        CircularProgressIndicator(
            modifier = Modifier
                .size(160.dp)
                .testTag(PROGRESS_TAG),
            color = Color.Cyan,
        )
    }
}
