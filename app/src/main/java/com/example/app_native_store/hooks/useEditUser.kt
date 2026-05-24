import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun useEditUser(
    id: String?,
    snackbarHostState: SnackbarHostState,
    viewModel: EditUserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
): EditUserHook {
    val usuario by viewModel.usuario.collectAsState()
    val error   by viewModel.error.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val saved   by viewModel.saved.collectAsState()
    val scope   = rememberCoroutineScope()

    LaunchedEffect(id) { id?.let { viewModel.loadUsuario(it) } }
    LaunchedEffect(saved) { if (saved) scope.launch { snackbarHostState.showSnackbar("Datos actualizados") } }

    var edit        by remember { mutableStateOf(false) }
    var nombre      by remember { mutableStateOf("") }
    var telefono    by remember { mutableStateOf("") }
    var genero      by remember { mutableStateOf("") }
    var edad        by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var ciudad      by remember { mutableStateOf("") }
    var nuevaClave  by remember { mutableStateOf("") }

    LaunchedEffect(usuario) {
        usuario?.let {
            nombre      = it.nombre
            telefono    = it.telefono ?: ""
            genero      = it.genero ?: ""
            edad        = it.edad?.toString() ?: ""
            descripcion = it.descripcionPerfil ?: ""
            ciudad      = it.ciudad ?: ""
        }
    }

    return EditUserHook(
        usuario = usuario, error = error, loading = loading, edit = edit,
        nombre = nombre, telefono = telefono, genero = genero, edad = edad,
        descripcion = descripcion, ciudad = ciudad, nuevaClave = nuevaClave,
        onNombreChange = { nombre = it },
        onTelefonoChange = { telefono = it },
        onGeneroChange = { genero = it },
        onEdadChange = { edad = it },
        onDescripcionChange = { descripcion = it },
        onCiudadChange = { ciudad = it },
        onNuevaClaveChange = { nuevaClave = it },
        onEditarGuardar = { userId ->
            if (edit) {
                viewModel.guardar(userId, nombre, telefono, ciudad, nuevaClave)
                edit = false
            } else {
                edit = true
            }
        }
    )
}