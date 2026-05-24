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
fun useTienda(snackbarHostState: SnackbarHostState): TiendaHook {
    val ownerId = UserSesion.usuario?.id as String
    val scope = rememberCoroutineScope()

    var cargando    by remember { mutableStateOf(true) }
    var errorMsg    by remember { mutableStateOf<String?>(null) }
    var tienda      by remember { mutableStateOf<Store?>(null) }
    var name        by remember { mutableStateOf("") }
    var address     by remember { mutableStateOf("") }
    var phone       by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var edit        by remember { mutableStateOf(false) }
    var guardando   by remember { mutableStateOf(false) }
    var unas        by remember { mutableStateOf(false) }
    var pestanas    by remember { mutableStateOf(false) }
    var cejas       by remember { mutableStateOf(false) }
    var barberia    by remember { mutableStateOf(false) }
    var cortesDama  by remember { mutableStateOf(false) }

    LaunchedEffect(ownerId) {
        cargando = true
        val (store, error) = getStoreByOwner(ownerId)
        if (error != null) {
            errorMsg = error
        } else if (store == null) {
            errorMsg = "No se encontró la tienda"
        } else {
            tienda      = store
            name        = store.name
            address     = store.address
            phone       = store.phone ?: ""
            description = store.description ?: ""
            unas        = store.types.contains("uñas")
            pestanas    = store.types.contains("pestañas")
            cejas       = store.types.contains("cejas")
            barberia    = store.types.contains("barberia")
            cortesDama  = store.types.contains("cortes para dama")
        }
        cargando = false
    }

    return TiendaHook(
        tienda = tienda, name = name, address = address, phone = phone,
        description = description, edit = edit, guardando = guardando,
        cargando = cargando, errorMsg = errorMsg,
        unas = unas, pestanas = pestanas, cejas = cejas,
        barberia = barberia, cortesDama = cortesDama,
        onNameChange = { name = it },
        onAddressChange = { address = it },
        onPhoneChange = { phone = it },
        onDescriptionChange = { description = it },
        onUnasChange = { unas = it },
        onPestanasChange = { pestanas = it },
        onCejasChange = { cejas = it },
        onBarberiaChange = { barberia = it },
        onCortesDamaChange = { cortesDama = it },
        onEditarGuardar = {
            if (guardando) return@TiendaHook
            if (edit) {
                scope.launch {
                    guardando = true
                    try {
                        val selectedTypes = listOfNotNull(
                            "uñas".takeIf { unas },
                            "pestañas".takeIf { pestanas },
                            "cejas".takeIf { cejas },
                            "barberia".takeIf { barberia },
                            "cortes para dama".takeIf { cortesDama }
                        )
                        val error = updateStore(
                            storeId = tienda!!.id,
                            store = tienda!!.copy(
                                name = name, address = address,
                                phone = phone, description = description,
                                types = selectedTypes
                            )
                        )
                        if (error == null) { snackbarHostState.showSnackbar("Tienda actualizada"); edit = false }
                        else snackbarHostState.showSnackbar(error)
                    } catch (e: Exception) {
                        snackbarHostState.showSnackbar("Error")
                    } finally {
                        guardando = false
                    }
                }
            } else { edit = true }
        }
    )
}