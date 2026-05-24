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

@Composable
fun AppointmentComp(
    appointment: Appointment,
    onDelete: (id: String) -> Unit,
    onRate: (id: String, score: Int) -> Unit
) {
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
            Text(text = appointment.service, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "${appointment.date} - ${appointment.time}", fontSize = 13.sp)
            Text(text = appointment.address, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

            appointment.comment?.let {
                Text(text = it, fontSize = 12.sp)
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Surface(
                    color = when (appointment.status.lowercase()) {
                        stringResource(R.string.pendiente)  -> MaterialTheme.colorScheme.tertiaryContainer
                        stringResource(R.string.confirmada) -> MaterialTheme.colorScheme.primaryContainer
                        stringResource(R.string.completada) -> MaterialTheme.colorScheme.secondaryContainer
                        else         -> MaterialTheme.colorScheme.surfaceVariant
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = appointment.status,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            if (appointment.status.lowercase() in listOf( stringResource(R.string.pendiente),  stringResource(R.string.confirmada))) {
                ButtonComponent(
                    onClick = { onDelete(appointment.id) },
                    textBtn = if (appointment.status.lowercase() ==  stringResource(R.string.pendiente))  stringResource(R.string.eliminar) else stringResource(
                        R.string.cancelar_cita
                    )
                )
            }

            if (appointment.status.lowercase() ==  stringResource(R.string.completada)) {
                if (appointment.score != null) {
                    ScoreFixed(rating = appointment.score.toInt())
                } else {
                    RatingStars(
                        rating = rating,
                        onRatingChange = { newRating ->
                            rating = newRating
                            onRate(appointment.id, newRating)
                        }
                    )
                }
            }
        }
    }
}