//package design
//
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import com.example.musify.domain.Genre
//
///**
// * A card that represents a Genre.
// *
// * @param genre the instance of [Genre] that the card is modeling.
// * @param imageResourceId a resource id that specifies the image to
// * be loaded as the thumbnail of the genre card.
// * @param modifier modifier to be applied to this composable.
// * @param onClick the lambda to execute when the card is clicked.
// * @param backgroundColor the background color.
// */
//@Composable
//fun GenreCard(
//    genre: Genre,
//    @DrawableRes imageResourceId: Int,
//    modifier: Modifier = Modifier,
//    onClick: (() -> Unit)? = null,
//    backgroundColor: Color
//) {
//    Card(
//        modifier = modifier
//            .background(backgroundColor)
//            .clickable { onClick?.invoke() },
//    ) {
//        Box(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Image(
//                modifier = Modifier
//                    .size(90.dp)
//                    .offset(x = 110.dp, y = 30.dp)
//                    .rotate(30f),
//                painter = painterResource(id = imageResourceId),
//                contentDescription = null
//            )
//            Text(
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(16.dp),
//                text = genre.label,
//                fontWeight = FontWeight.Bold,
//                style = MaterialTheme.typography.headlineSmall
//            )
//        }
//    }
//}