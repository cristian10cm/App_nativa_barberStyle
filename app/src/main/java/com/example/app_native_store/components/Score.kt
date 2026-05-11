import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun RatingStars(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    Row {
        for (i in 1..5) {
            IconButton(
                onClick = { onRatingChange(i) }
            ) {
                Icon(
                    imageVector = if (i <= rating) {
                        Icons.Filled.Star
                    } else {
                        Icons.Outlined.StarBorder
                    },
                    contentDescription = "Star $i",
                    tint = if (i <= rating) Color(0xFFFFC107) else Color.Gray
                )
            }
        }
    }
}