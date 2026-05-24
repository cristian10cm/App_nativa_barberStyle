import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.app_native_store.R
import com.example.app_native_store.ui.components.FilterChips

@Composable
fun AgendaUser() {
    val h = useAgendaUser()

    Column(modifier = Modifier.fillMaxWidth()) {
        Header( stringResource(R.string.agenda_de_citas))

        h.error?.let { Text(text = it, color = Color.Red) }

        FilterChips(
            types = h.allTypes,
            selectedType = h.selectedType,
            onTypeSelected = h.onTypeSelected
        )

        LazyColumn {
            items(h.filteredAppointments) { app ->
                AppointmentComp(
                    appointment = app,
                    onDelete = h.onDelete,
                    onRate = h.onRate
                )
            }
        }
    }
}