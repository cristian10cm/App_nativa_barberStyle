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

fun AgendaUser(){
    var selectedType by remember { mutableStateOf<String?>(null) }
    val allTypes = listOf(
        "Confirmada", "Pendiente", "Completada", "Rechazada", "Cancelada", "Todas"
    )

    val filteredAppointments = if (selectedType == null || selectedType == "Todas") {
        appointmentsList
    } else {
        appointmentsList.filter { app ->
            app.status == selectedType
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Header("Agenda de citas")
        FilterChips(
            types = allTypes,
            selectedType = selectedType,
            onTypeSelected = { selectedType = it }
        )
        LazyColumn()  {
            items(filteredAppointments){
                    app->
                AppointmentComp (
                    app,
                    onDelete = {
                        removeAppointment(app)
                    }
                )
            }
        }
    }
}