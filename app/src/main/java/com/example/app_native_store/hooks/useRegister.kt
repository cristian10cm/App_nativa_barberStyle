import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun useRegister(
    navController: NavController,
    snackbarHostState: SnackbarHostState
): RegisterHook {
    var nombre            by remember { mutableStateOf("") }
    var correo            by remember { mutableStateOf("") }
    var password          by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }
    var telefono          by remember { mutableStateOf("") }
    var genero            by remember { mutableStateOf("") }
    var edad              by remember { mutableStateOf("") }
    var descripcionPerfil by remember { mutableStateOf("") }
    var ciudad            by remember { mutableStateOf("") }
    val roles = listOf("usuario" to "Usuario", "dueno_tienda" to "Dueño de tienda")
    var rolSeleccionado   by remember { mutableStateOf(roles[0]) }
    var dropdownExpanded  by remember { mutableStateOf(false) }
    var cargando          by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    return RegisterHook(
        nombre = nombre, correo = correo, password = password,
        confirmarPassword = confirmarPassword, telefono = telefono,
        genero = genero, edad = edad, descripcionPerfil = descripcionPerfil,
        ciudad = ciudad, roles = roles, rolSeleccionado = rolSeleccionado,
        dropdownExpanded = dropdownExpanded, cargando = cargando,
        onNombreChange = { nombre = it },
        onCorreoChange = { correo = it },
        onPasswordChange = { password = it },
        onConfirmarPasswordChange = { confirmarPassword = it },
        onTelefonoChange = { telefono = it },
        onGeneroChange = { genero = it },
        onEdadChange = { edad = it },
        onDescripcionChange = { descripcionPerfil = it },
        onCiudadChange = { ciudad = it },
        onRolSelected = { rolSeleccionado = it; dropdownExpanded = false },
        onDropdownToggle = { dropdownExpanded = !dropdownExpanded },
        onDropdownDismiss = { dropdownExpanded = false },
        onRegistrar = {
            if (cargando) return@RegisterHook
            scope.launch {
                val validacion = when {
                    nombre.isBlank()  -> "El nombre es obligatorio"
                    correo.isBlank()  -> "El correo es obligatorio"
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> "Correo inválido"
                    password.isBlank() -> "La contraseña es obligatoria"
                    password.length < 6 -> "La contraseña debe tener al menos 6 caracteres"
                    password != confirmarPassword -> "Las contraseñas no coinciden"
                    else -> null
                }
                if (validacion != null) { snackbarHostState.showSnackbar(validacion); return@launch }
                cargando = true
                try {
                    val (nuevoId, errorUsuario) = registrarUsuario(
                        nombre = nombre.trim(), correo = correo.trim(),
                        password = password, rol = rolSeleccionado.first,
                        telefono = telefono.trim(), genero = genero.trim(),
                        edad = edad.toIntOrNull(), descripcionPerfil = descripcionPerfil.trim(),
                        ciudad = ciudad.trim()
                    )
                    if (errorUsuario != null) { snackbarHostState.showSnackbar(errorUsuario); return@launch }
                    if (rolSeleccionado.first == "dueno_tienda" && nuevoId != null) {
                        val errorTienda = crearTiendaAutomatica(nombreDueno = nombre.trim(), ownerId = nuevoId)
                        if (errorTienda != null) {
                            navController.popBackStack()
                            return@launch
                        }
                    }
                    snackbarHostState.showSnackbar(
                        if (rolSeleccionado.first == "dueno_tienda") "Cuenta creada con exito"
                        else "Cuenta creada con éxito"
                    )
                    navController.popBackStack()
                } catch (e: Exception) {
                    snackbarHostState.showSnackbar("Excepción: ${e.message}")
                } finally {
                    cargando = false
                }
            }
        }
    )
}