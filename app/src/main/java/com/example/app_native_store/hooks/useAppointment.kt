import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.app_native_store.network.UserSesion

@Composable
fun useAgendaUser(
    viewModel: AgendaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
): AgendaUserHook {
    val appointments by viewModel.appointments.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAppointments(UserSesion.usuario?.id as String)
    }
    var selectedType by remember { mutableStateOf<String?>(null) }
    val allTypes = listOf("aceptada", "pendiente", "completada", "cancelada", "Todas")

    val filteredAppointments = if (selectedType == null || selectedType == "Todas") {
        appointments
    } else {
        appointments.filter { it.status == selectedType }
    }
    return AgendaUserHook(
        filteredAppointments = filteredAppointments,
        error = error,
        allTypes = allTypes,
        selectedType = selectedType,
        onTypeSelected = { selectedType = it },
        onDelete = { viewModel.removeAppointment(it) },
        onRate = { id, score -> viewModel.rateAppointment(id, score) }
    )
}