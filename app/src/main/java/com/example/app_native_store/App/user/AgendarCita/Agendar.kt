import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_native_store.types.AppointmentType
import com.example.app_native_store.utils.SearchStoreFun

@Composable
fun Agendar(id: String?,navController: NavController) {

    val store = SearchStoreFun(id as String)

    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var comentario by remember { mutableStateOf("") }
    var selectedService by remember { mutableStateOf("") }
    fun newAppoiment(){
        AddAppoiment(
            appointment =
                AppointmentType(
                    id = store.id.toString(),
                    storeName = store.name,
                    service = selectedService,
                    date = date,
                    time = time,
                    status = "pendiente",
                    address = store.address,
                    comentario,
                    userId = "1"
                )
        )
        navController.navigate("agenda")

    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(name = "Agendar Cita")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            StoreCard(store)

            store?.let {
                SelectComp(
                    label = "Servicio",
                    options = it.types,
                    onValueChange = { selectedService = it }
                )
            }

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Fecha") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("Hora") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = comentario,
                onValueChange = { comentario = it },
                label = { Text("Comentario") },
                modifier = Modifier.fillMaxWidth()
            )

            ButtonComponent(
                onClick = {
                    newAppoiment()
                },
                textBtn = "Agendar cita"
            )
        }
    }
}