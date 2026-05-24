import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun useUsuarios(
    navController: NavController,
    viewModel: UsuariosViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
): UsuariosHook {
    val usuarios by viewModel.usuarios.collectAsState()
    val error    by viewModel.error.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadUsuarios() }

    var selectedType by remember { mutableStateOf<String?>(null) }
    val allTypes = listOf("Usuarios", "Tiendas", "Todos")

    val filteredUsers = if (selectedType == null || selectedType == "Todos") {
        usuarios
    } else {
        if (selectedType == "Usuarios") usuarios.filter { it.rol == "usuario" }
        else usuarios.filter { it.rol == "dueno_tienda" }
    }

    return UsuariosHook(
        filteredUsers = filteredUsers,
        error = error,
        allTypes = allTypes,
        selectedType = selectedType,
        onTypeSelected = { selectedType = it },
        onEdit = { id -> navController.navigate("verUsuarios/$id") }
    )
}