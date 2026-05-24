import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_native_store.R

@Composable
fun EditUser(id: String?) {
    val snackbarHostState = remember { SnackbarHostState() }
    val h = useEditUser(id, snackbarHostState)

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Header(name = stringResource(R.string.cuenta))

            h.error?.let { Text(text = it, color = Color.Red, modifier = Modifier.padding(16.dp)) }
            if (h.loading) Text(stringResource(R.string.cargando), modifier = Modifier.padding(16.dp))

            h.usuario?.let { user ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                        .padding(15.dp)
                ) {
                    Text(fontWeight = FontWeight.Bold, text =  stringResource(R.string.informaci_n_sobre_la_cuenta))
                    Spacer(modifier = Modifier.height(15.dp))

                    OutlinedTextField(value = h.nombre, onValueChange = h.onNombreChange, label = { Text( stringResource(R.string.nombre)) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = h.telefono, onValueChange = h.onTelefonoChange, label = { Text( stringResource(R.string.tel_fono)) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = h.genero, onValueChange = h.onGeneroChange, label = { Text( stringResource(R.string.g_nero)) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = h.edad, onValueChange = h.onEdadChange, label = { Text( stringResource(R.string.edad)) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = h.descripcion, onValueChange = h.onDescripcionChange, label = { Text( stringResource(R.string.descripci_n_de_perfil)) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = h.ciudad, onValueChange = h.onCiudadChange, label = { Text( stringResource(R.string.ciudad)) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = user.fechaCreacion ?: "", onValueChange = {}, label = { Text( stringResource(R.string.fecha_de_creaci_n)) }, enabled = false, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = user.password, onValueChange = {}, label = { Text(
                        stringResource(R.string.clave_actual)
                    ) }, enabled = false, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = h.nuevaClave, onValueChange = h.onNuevaClaveChange, label = { Text( stringResource(R.string.cambiar_contrase_a)) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = user.correo, onValueChange = {}, label = { Text( stringResource(R.string.correo)) }, enabled = false, singleLine = true, modifier = Modifier.fillMaxWidth())

                    ButtonComponent(
                        onClick = { h.onEditarGuardar(user.id) },
                        textBtn = if (h.edit)  stringResource(R.string.guardar) else  stringResource(R.string.editar)
                    )
                }
            }
        }
    }
}