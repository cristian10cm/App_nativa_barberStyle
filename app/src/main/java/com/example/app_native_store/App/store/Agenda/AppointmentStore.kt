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
fun AgendaStore() {
    val hook = useAgendaStore()

    Column(modifier = Modifier.fillMaxWidth()) {
        Header(stringResource(R.string.tu_agenda_de_citas))

        hook.error?.let { Text(text = it, color = Color.Red) }

        FilterChips(
            types = hook.allTypes,
            selectedType = hook.selectedType,
            onTypeSelected = hook.onTypeSelected
        )

        LazyColumn {
            items(hook.filteredAppointments) { app ->
                AppointmentCompStore(
                    appointment = app,
                    onChangeStatus = { id, newStatus -> hook.changeStatus(id, newStatus) }
                )
            }
        }
    }
}