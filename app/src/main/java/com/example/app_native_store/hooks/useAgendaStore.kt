import androidx.compose.runtime.*
import com.example.app_native_store.network.UserSesion

@Composable
fun useAgendaStore(
    viewModel: AgendaStoreViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
): AgendaStoreHook {

    val appointments by viewModel.appointments.collectAsState()
    val error by viewModel.error.collectAsState()
    LaunchedEffect(Unit) {
        val ownerId = UserSesion.usuario?.id ?: return@LaunchedEffect
        viewModel.loadStoreAndAppointments(ownerId)
    }
    var selectedType by remember { mutableStateOf<String?>(null) }
    val allTypes = listOf("aceptada", "pendiente", "completada", "cancelada", "Todas")
    val filteredAppointments = if (selectedType == null || selectedType == "Todas") {
        appointments
    } else {
        appointments.filter { it.status == selectedType }
    }
    return AgendaStoreHook(
        appointments = appointments,
        error = error,
        allTypes = allTypes,
        filteredAppointments = filteredAppointments,
        selectedType = selectedType,
        onTypeSelected = { selectedType = it },
        changeStatus = viewModel::changeStatus
    )
}