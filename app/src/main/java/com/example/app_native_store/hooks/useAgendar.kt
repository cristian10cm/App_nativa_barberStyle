import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun useAgendar(
    id: String?,
    navController: NavController,
    viewModel: AgendarViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
): AgendarHook {
    val store           by viewModel.store.collectAsState()
    val loading         by viewModel.loading.collectAsState()
    val error           by viewModel.error.collectAsState()
    val appointmentDone by viewModel.appointmentDone.collectAsState()

    LaunchedEffect(id) {
        id?.let { viewModel.loadStore(it) }
    }

    LaunchedEffect(appointmentDone) {
        if (appointmentDone) navController.navigate("agenda")
    }

    var date            by remember { mutableStateOf("") }
    var time            by remember { mutableStateOf("") }
    var comentario      by remember { mutableStateOf("") }
    var selectedService by remember { mutableStateOf("") }
    val dias = listOf(
        "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"
    )

    val horas = listOf(
        "8:00 AM", "8:30 AM",
        "9:00 AM", "9:30 AM",
        "10:00 AM", "10:30 AM",
        "11:00 AM", "11:30 AM",
        "12:00 PM", "12:30 PM",
        "1:00 PM", "1:30 PM",
        "2:00 PM", "2:30 PM",
        "3:00 PM", "3:30 PM",
        "4:00 PM", "4:30 PM",
        "5:00 PM", "5:30 PM",
        "6:00 PM", "6:30 PM",
        "7:00 PM", "7:30 PM",
        "8:00 PM"
    )
    return AgendarHook(
        store = store, loading = loading, error = error,
        date = date, time = time, comentario = comentario,
        selectedService = selectedService,
        onDateChange = { date = it },
        onTimeChange = { time = it },
        onComentarioChange = { comentario = it },
        onServiceChange = { selectedService = it },
        onAgendar = {
            viewModel.createNewAppointment(
                service = selectedService,
                date    = date,
                time    = time,
                comment = comentario
            )
        },
        dias = dias,
        horas = horas

    )
}
