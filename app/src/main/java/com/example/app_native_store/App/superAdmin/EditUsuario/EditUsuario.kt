import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
@Composable

fun EditUser(id: String?) {
    var user = FindUserId(id.toString())
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf(user.nombre) }
    var telefono by remember { mutableStateOf(user.telefono) }
    var genero by remember { mutableStateOf(user.genero) }
    var edad by remember { mutableStateOf(user.edad.toString()) }
    var descripcion by remember { mutableStateOf(user.descripcionPerfil) }
    var ciudad by remember { mutableStateOf(user.ciudad) }
    var edit by remember { mutableStateOf(false) }
    var clave by remember { mutableStateOf(user.password) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier.fillMaxSize()

                .verticalScroll(rememberScrollState())
        ) {

            Header(name = "Cuenta")
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp)
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                    .padding(15.dp),

                ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Información sobre la cuenta"
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    enabled = edit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    enabled = edit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Género") },
                    enabled = edit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = edad.toString(),
                    onValueChange = { edad = it },
                    label = { Text("Edad") },
                    enabled = edit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción de perfil") },
                    enabled = edit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = ciudad,
                    onValueChange = { ciudad = it },
                    label = { Text("Ciudad") },
                    enabled = edit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = user.fechaCreacion,
                    onValueChange = { },
                    label = { Text("Fecha de creación de cuenta") },
                    enabled = false,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = clave,
                    onValueChange = { clave = it },
                    label = { Text("Clave actual") },
                    enabled = false,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = clave,
                    onValueChange = { clave = it },
                    label = { Text("Cambiar clave") },
                    enabled = edit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = user.correo,
                    onValueChange = { },
                    label = { Text("Correo") },
                    enabled = false,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                ButtonComponent(
                    onClick = {
                        if (edit) {
                            actualizarUsuario(
                                nombre,
                                telefono,
                                genero,
                                edad,
                                descripcion,
                                ciudad,
                                clave
                            )
                            edit = false
                            updateUser(
                                user.copy(
                                    nombre = nombre,
                                    telefono = telefono,
                                    genero = genero,
                                    edad = edad.toIntOrNull() ?: user.edad,
                                    descripcionPerfil = descripcion,
                                    ciudad = ciudad,
                                    password = clave
                                )
                            )
                            scope.launch {
                                snackbarHostState.showSnackbar("Datos actualizados")
                            }
                        } else {
                            edit = true
                        }
                    },
                    textBtn = if (edit) "Guardar" else "Editar"
                )
            }


        }
    }
}