import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.app_native_store.network.UserSesion
import kotlinx.coroutines.launch

@Composable
fun useAccountStore(snackbarHostState: SnackbarHostState): AccountStoreHook {
    val userId = UserSesion.usuario?.id as String
    val scope = rememberCoroutineScope()
    var cargando       by remember { mutableStateOf(true) }
    var errorMsg       by remember { mutableStateOf<String?>(null) }
    var usuario        by remember { mutableStateOf<Usuario?>(null) }
    var nombre         by remember { mutableStateOf("") }
    var telefono       by remember { mutableStateOf("") }
    var ciudad         by remember { mutableStateOf("") }
    var edit           by remember { mutableStateOf(false) }
    var guardando      by remember { mutableStateOf(false) }
    var editClave      by remember { mutableStateOf(false) }
    var claveActual    by remember { mutableStateOf("") }
    var nuevaClave     by remember { mutableStateOf("") }
    var claveIncorrecta by remember { mutableStateOf(false) }
    var guardandoClave by remember { mutableStateOf(false) }
    LaunchedEffect(userId) {
        cargando = true
        val (user, error) = getUsuarioById(userId)
        if (error != null) {
            errorMsg = error
        } else if (user == null) {
            errorMsg = "No se encontró el usuario"
        } else {
            usuario  = user
            nombre   = user.nombre
            telefono = user.telefono ?: ""
            ciudad   = user.ciudad ?: ""
        }
        cargando = false
    }
    return AccountStoreHook(
        usuario = usuario, nombre = nombre, telefono = telefono, ciudad = ciudad,
        edit = edit, guardando = guardando, editClave = editClave,
        claveActual = claveActual, nuevaClave = nuevaClave,
        claveIncorrecta = claveIncorrecta, guardandoClave = guardandoClave,
        cargando = cargando, errorMsg = errorMsg,
        onNombreChange = { nombre = it },
        onTelefonoChange = { telefono = it },
        onCiudadChange = { ciudad = it },
        onClaveActualChange = { claveActual = it; claveIncorrecta = false },
        onNuevaClaveChange = { nuevaClave = it },
        onEditarGuardar = {
            if (guardando) return@AccountStoreHook
            if (edit) {
                scope.launch {
                    guardando = true
                    try {
                        val error = updateUsuario(id = userId, nombre = nombre.trim(), telefono = telefono.trim(), ciudad = ciudad.trim())
                        if (error == null) { snackbarHostState.showSnackbar("Datos actualizados"); edit = false }
                        else snackbarHostState.showSnackbar(error)
                    } catch (e: Exception) {
                        snackbarHostState.showSnackbar("Error: ${e.message}")
                    } finally {
                        guardando = false
                    }
                }
            } else { edit = true }
        },
        onCambiarClave = {
            if (guardandoClave) return@AccountStoreHook
            if (!editClave) { editClave = true; claveIncorrecta = false; return@AccountStoreHook }
            if (claveActual != usuario?.password) { claveIncorrecta = true; return@AccountStoreHook }
            if (nuevaClave.length < 6) {
                scope.launch { snackbarHostState.showSnackbar("La nueva contraseña debe tener al menos 6 caracteres") }
                return@AccountStoreHook
            }
            scope.launch {
                guardandoClave = true
                try {
                    val error = updatePassword(userId, nuevaClave)
                    if (error == null) {
                        usuario = usuario?.copy(password = nuevaClave)
                        snackbarHostState.showSnackbar("Contraseña actualizada")
                        editClave = false; claveActual = ""; nuevaClave = ""
                    } else snackbarHostState.showSnackbar(error)
                } catch (e: Exception) {
                    snackbarHostState.showSnackbar("Error")
                } finally {
                    guardandoClave = false
                }
            }
        }
    )
}