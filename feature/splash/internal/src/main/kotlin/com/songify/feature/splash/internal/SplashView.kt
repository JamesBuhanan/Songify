package com.songify.feature.splash.internal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.songify.common.di.AppScope
import com.songify.feature.splash.SplashScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import kotlinx.coroutines.delay


@CircuitInject(SplashScreen::class, AppScope::class)
@Composable
fun SplashView(
    splashState: SplashState,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(splashState) {
        delay(2000)
        splashState.eventSink(SplashEvent.AnimationFinished)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.lemmus),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Songify",
            color = Color.Black,
            fontSize = 80.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.padding(top = 350.dp)
        )
    }
}
