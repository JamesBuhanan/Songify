//package design
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.material.*
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.FilterChip
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//
///**
// * A filter chip composable.
// * @param text the text that is displayed within chip.
// * @param onClick callback that will be executed when the user clicks
// * the chip.
// * @param modifier Modifier to be applied to the button
// * @param isSelected indicates whether the filter chip is selected or not.
// * Based on the selected state of the composable, different styles will be
// * applied.
// */
//@Composable
//fun MusifyFilterChip(
//    text: String,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    isSelected: Boolean = false
//) {
//    FilterChip(
//        modifier = modifier,
//        selected = isSelected,
//        onClick = onClick,
//        border = if (isSelected) BorderStroke(1.dp, MaterialTheme.colors.primary)
//        else ButtonDefaults.outlinedBorder,
//        colors = ChipDefaults.filterChipColors(
//            backgroundColor = if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.7f)
//            else MaterialTheme.colors.surface
//        ),
//        content = { Text(text = text, color = Color.White) }
//    )
//}