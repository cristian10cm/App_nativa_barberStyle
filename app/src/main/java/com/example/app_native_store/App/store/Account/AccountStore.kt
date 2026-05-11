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
import androidx.compose.ui.unit.sp
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
@Composable

fun AccountStore() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf(UserInfo.nombre) }
    var telefono by remember { mutableStateOf(UserInfo.telefono) }
    var ciudad by remember { mutableStateOf(UserInfo.ciudad) }
    var edit by remember { mutableStateOf(false) }
    var editClave by remember { mutableStateOf(false) }
    var clave by remember { mutableStateOf("") }
    var newClave by remember { mutableStateOf("") }
    var messageClaveBad by remember { mutableStateOf(false) }
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
                    value = ciudad,
                    onValueChange = { ciudad = it },
                    label = { Text("Ciudad") },
                    enabled = edit,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = "Tienda",
                    onValueChange = { },
                    label = { Text("Rol") },
                    enabled = false,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = UserInfo.fechaCreacion,
                    onValueChange = { },
                    label = { Text("Fecha de creación de cuenta") },
                    enabled = false,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = UserInfo.correo,
                    onValueChange = { },
                    label = { Text("Correo") },
                    enabled = false,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                ButtonComponent(
                    onClick = {
                        if (edit) {
                            actualizarUserStore(
                                nombre,
                                telefono,
                                ciudad,
                                contraseña = UserInfo.password
                            )
                            edit = false
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
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp)
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                    .padding(15.dp),
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Contraseña"
                )
                Spacer(modifier = Modifier.height(15.dp))
                if (editClave) {
                    OutlinedTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Clave antigua") },
                        enabled = true,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newClave,
                        onValueChange = { newClave = it },
                        label = { Text("Ingresa tu nueva clave") },
                        enabled = true,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (messageClaveBad) {
                        Text(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = Color.Red,
                            text = "La clave antigua debe ser la correcta para poder cambiarla*"
                        )
                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                ButtonComponent(
                    textBtn = "Cambiar contraseña",
                    onClick = {
                        if (!editClave) {
                            editClave = true
                            messageClaveBad = false
                        } else {
                            if (UserInfo.password == clave) {
                                actualizarUserStore(
                                    UserInfo.nombre,
                                    UserInfo.telefono,
                                    UserInfo.ciudad,
                                    newClave
                                )
                                editClave = false
                                clave = ""
                                newClave = ""
                                scope.launch {
                                    snackbarHostState.showSnackbar("Contraseña actualizada")
                                }
                            } else {
                                messageClaveBad = true
                            }
                        }
                    }
                )
            }

        }
    }
}