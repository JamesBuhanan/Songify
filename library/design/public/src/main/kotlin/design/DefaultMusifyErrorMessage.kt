package design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * A composable that is used to display an error message through-out
 * the app with the specified [title] and [subtitle]. It manages the
 * styling of the both the [title] and [subtitle].
 * @param modifier the modifier to be applied to the composable.
 */
@Deprecated(message = "Use the other overload.")
@Composable
fun DefaultMusifyErrorMessage(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = subtitle,
            style = LocalTextStyle.current.copy(
                color = Color.White.copy(alpha = 0.38f)
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(50),
            content = { Text(text = "Retry") }
        )
    }
}

/**
 * A composable that is used to display an error message through-out
 * the app with the specified [title] and [subtitle]. It manages the
 * styling of the both the [title] and [subtitle]. This composable
 * is an overload of [DefaultMusifyErrorMessage] that also displays
 * a retry button.
 * @param modifier the modifier to be applied to the composable.
 * @param onRetryButtonClicked the lambda to execute when the retry
 * button is clicked.
 */
@Composable
fun DefaultMusifyErrorMessage(
    title: String,
    subtitle: String,
    onRetryButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = subtitle,
            style = LocalTextStyle.current.copy(
                color = Color.White.copy(alpha = 0.38f)
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedButton(
            onClick = onRetryButtonClicked,
            shape = RoundedCornerShape(50),
            content = { Text(text = "Retry") }
        )
    }
}