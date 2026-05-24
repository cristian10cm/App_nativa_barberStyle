import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_native_store.R

@Composable
fun AppointmentCompStore(
    appointment: Appointment,
    onChangeStatus: (id: String, statusApp: String) -> Unit
) {
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
            Text(text = appointment.service, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)

            appointment.comment?.let {
                Text(text = it, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Text(text = "${appointment.date} - ${appointment.time}", fontSize = 13.sp)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Surface(
                    color = when (appointment.status.lowercase()) {
                        stringResource(R.string.pendiente)  -> MaterialTheme.colorScheme.tertiaryContainer
                        stringResource(R.string.aceptada) -> MaterialTheme.colorScheme.primaryContainer
                        stringResource(R.string.completada) -> MaterialTheme.colorScheme.secondaryContainer
                        stringResource(R.string.rechazada) -> MaterialTheme.colorScheme.secondaryContainer
                        stringResource(R.string.cancelada) -> MaterialTheme.colorScheme.secondaryContainer
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

            if (appointment.status.lowercase() == stringResource(R.string.pendiente)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { onChangeStatus(appointment.id, "aceptada") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF102922)),
                        modifier = Modifier.weight(1f)
                    ) { Text(stringResource(R.string.aceptar)) }

                    Button(
                        onClick = { onChangeStatus(appointment.id, "cancelada") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.weight(1f)
                    ) { Text("Cancelar") }
                }
            } else if (appointment.status.lowercase() == "aceptada") {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { onChangeStatus(appointment.id, "completada") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                        modifier = Modifier.weight(1f)
                    ) { Text( stringResource(R.string.completar)) }

                    Button(
                        onClick = { onChangeStatus(appointment.id, "cancelada") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier.weight(1f)
                    ) { Text(stringResource(R.string.cancelar), color = Color.White) }
                }
            }
        }
    }
}