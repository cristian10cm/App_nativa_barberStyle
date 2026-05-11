import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_native_store.R
import com.example.app_native_store.types.AppointmentType

@Composable
fun AppointmentCompStore(appoinment: AppointmentType, onChangeStatus:(id:String,statusApp:String)-> Unit) {
    var rating by remember { mutableStateOf(0) }
    var user = FindUserId(appoinment.userId)
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
                text = user.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = appoinment.service,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )


            Text(
                text = appoinment.comment,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${appoinment.date} - ${appoinment.time}",
                fontSize = 13.sp
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
                        "rechazada" -> MaterialTheme.colorScheme.secondaryContainer
                        "cancelada" -> MaterialTheme.colorScheme.secondaryContainer
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
            if (appoinment.status == stringResource(R.string.pendiente)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Button(
                        onClick = {
                            onChangeStatus(appoinment.id, "Confirmada")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF102922)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.confirmar))
                    }

                    Button(
                        onClick = {
                            onChangeStatus(appoinment.id, "Rechazada")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.rechazar))
                    }
                }

            } else if (appoinment.status == stringResource(R.string.confirmada)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Button(
                        onClick = {
                            onChangeStatus(appoinment.id, "Completada")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.completar))
                    }

                    Button(
                        onClick = {
                            onChangeStatus(appoinment.id, "Cancelada")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.cancelar), color = Color.White)

                    }
                }
            }
        }
    }
}