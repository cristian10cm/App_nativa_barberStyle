import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_native_store.R
import com.example.app_native_store.types.AppointmentType

@Composable
fun AppointmentComp(appoinment: AppointmentType, onDelete:(id:String)-> Unit) {
    var rating by remember { mutableStateOf(0) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = appoinment.storeName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = appoinment.service,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "${appoinment.date} - ${appoinment.time}",
                fontSize = 13.sp
            )

            Text(
                text = appoinment.address,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    color = when (appoinment.status.lowercase()) {
                        "pendiente" -> MaterialTheme.colorScheme.tertiaryContainer
                        "confirmada" -> MaterialTheme.colorScheme.primaryContainer
                        "completada" -> MaterialTheme.colorScheme.secondaryContainer
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = appoinment.status,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            if(appoinment.status == "Pendiente"){
                Row() {
                    ButtonComponent (
                        onClick = {onDelete(appoinment.id)},
                        textBtn = stringResource(R.string.eliminar)
                    )
                }
            }else if (appoinment.status == "Confirmada"){
                Row() {
                    ButtonComponent (
                        onClick = {onDelete(appoinment.id)},
                        textBtn = "Cancelar cita"
                    )
                }
            }
            if(appoinment.score !== null  && appoinment.status == "Completada"){
                ScoreFixed(
                    rating = appoinment.score?.toInt() ?: 0
                )
            }else if(appoinment.score == null && appoinment.status == "Completada"){
                RatingStars(
                    rating = rating,
                    onRatingChange = { newRating ->
                        rating = newRating
                        NewScore(id = appoinment.id,rating)
                    }
                )
            }
        }
    }
}