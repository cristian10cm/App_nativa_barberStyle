import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable

fun Tienda() {

    val searchUsuario = storeList.find {
            st -> st.ownerId == StoreInfo.id
    }

    if (searchUsuario == null) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No se encontró la tienda")
        }

        return
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf(searchUsuario.name) }
    var address by remember { mutableStateOf(searchUsuario.address) }
    var phone by remember { mutableStateOf(searchUsuario.phone) }
    var description by remember { mutableStateOf(searchUsuario.description) }
    var photo by remember { mutableStateOf(searchUsuario.photo) }

    var edit by remember { mutableStateOf(false) }

    var unas by remember {
        mutableStateOf(searchUsuario.types.contains("uñas"))
    }

    var pestanas by remember {
        mutableStateOf(searchUsuario.types.contains("pestañas"))
    }

    var cejas by remember {
        mutableStateOf(searchUsuario.types.contains("cejas"))
    }

    var barberia by remember {
        mutableStateOf(searchUsuario.types.contains("barberia"))
    }

    var cortesDama by remember {
        mutableStateOf(searchUsuario.types.contains("cortes para dama"))
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            Header(name = "Mi Tienda")

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .border(
                        1.dp,
                        Color.Black,
                        RoundedCornerShape(4.dp)
                    )
                    .padding(15.dp)
            ) {

                Text(
                    text = "Información de la tienda",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    enabled = edit,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Dirección") },
                    enabled = edit,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Teléfono") },
                    enabled = edit,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    enabled = edit,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = photo,
                    onValueChange = { photo = it },
                    label = { Text("URL Foto") },
                    enabled = edit,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Tipos de servicio",
                    fontWeight = FontWeight.SemiBold
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = unas,
                        onCheckedChange = { unas = it },
                        enabled = edit
                    )
                    Text("uñas")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = pestanas,
                        onCheckedChange = { pestanas = it },
                        enabled = edit
                    )
                    Text("pestañas")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = cejas,
                        onCheckedChange = { cejas = it },
                        enabled = edit
                    )
                    Text("cejas")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = barberia,
                        onCheckedChange = { barberia = it },
                        enabled = edit
                    )
                    Text("barberia")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = cortesDama,
                        onCheckedChange = { cortesDama = it },
                        enabled = edit
                    )
                    Text("cortes para dama")
                }

                Spacer(modifier = Modifier.height(15.dp))

                ButtonComponent(
                    textBtn = if (edit) "Guardar" else "Editar",
                    onClick = {

                        if (edit) {

                            val selectedTypes = listOfNotNull(
                                "uñas".takeIf { unas },
                                "pestañas".takeIf { pestanas },
                                "cejas".takeIf { cejas },
                                "barberia".takeIf { barberia },
                                "cortes para dama".takeIf { cortesDama }
                            )

                            EditStore(
                                searchUsuario.copy(
                                    name = name,
                                    address = address,
                                    phone = phone,
                                    description = description,
                                    photo = photo,
                                    types = selectedTypes
                                )
                            )

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Tienda actualizada"
                                )
                            }

                            edit = false

                        } else {

                            edit = true
                        }
                    }
                )
            }
        }
    }
}