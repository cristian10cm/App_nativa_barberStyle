import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.app_native_store.data.appointmentsList
import com.example.app_native_store.ui.components.FilterChips


@Composable
fun AgendaStore() {

    var selectedType by remember { mutableStateOf<String?>(null) }

    var appointments by remember {
        mutableStateOf(appointmentsList.toMutableList())
    }

    val allTypes = listOf(
        "Confirmada", "Pendiente", "Completada", "Rechazada", "Cancelada", "Todas"
    )

    fun updateAppointmentStatus(id: String, newStatus: String) {
        appointments = appointments.map { app ->
            if (app.id == id) {
                app.copy(status = newStatus)
            } else app
        }.toMutableList()
    }

    val filteredAppointments = if (selectedType == null || selectedType == "Todas") {
        appointments
    } else {
        appointments.filter { it.status == selectedType }
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        Header("Agenda de citas")

        FilterChips(
            types = allTypes,
            selectedType = selectedType,
            onTypeSelected = { selectedType = it }
        )

        LazyColumn {
            items(filteredAppointments) { app ->

                AppointmentCompStore(
                    app,
                    onChangeStatus = { id, newStatus ->
                        updateAppointmentStatus(id, newStatus)
                    }
                )
            }
        }
    }
}