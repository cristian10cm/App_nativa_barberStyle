package com.example.app_native_store.ui
import ButtonComponent
import Header
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_native_store.R
import useTienda
@Composable
fun Tienda() {
    val snackbarHostState = remember { SnackbarHostState() }
    val h = useTienda(snackbarHostState)

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        when {
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Header(name = stringResource(R.string.mi_tienda))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                            .padding(15.dp)
                    ) {
                        Text(stringResource(R.string.informaci_n_de_la_tienda), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(value = h.name, onValueChange = h.onNameChange, label = { Text( stringResource(R.string.nombre)) }, enabled = h.edit, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = h.address, onValueChange = h.onAddressChange, label = { Text(
                            stringResource(R.string.direcci_n)
                        ) }, enabled = h.edit, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = h.phone, onValueChange = h.onPhoneChange, label = { Text( stringResource(R.string.tel_fono)) }, enabled = h.edit, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = h.description, onValueChange = h.onDescriptionChange, label = { Text(
                            stringResource(R.string.descripci_n)
                        ) }, enabled = h.edit, modifier = Modifier.fillMaxWidth())

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(stringResource(R.string.tipos_de_servicio), fontWeight = FontWeight.SemiBold)

                        Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = h.unas, onCheckedChange = h.onUnasChange, enabled = h.edit); Text(
                            stringResource(R.string.u_as)
                        ) }
                        Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = h.pestanas, onCheckedChange = h.onPestanasChange, enabled = h.edit); Text(
                            stringResource(R.string.pesta_as)
                        ) }
                        Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = h.cejas, onCheckedChange = h.onCejasChange, enabled = h.edit); Text(
                            stringResource(R.string.cejas)
                        ) }
                        Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = h.barberia, onCheckedChange = h.onBarberiaChange, enabled = h.edit); Text(
                            stringResource(R.string.barberia)
                        ) }
                        Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = h.cortesDama, onCheckedChange = h.onCortesDamaChange, enabled = h.edit); Text(
                            stringResource(R.string.cortes_para_dama)
                        ) }

                        Spacer(modifier = Modifier.height(15.dp))
                        ButtonComponent(
                            textBtn = when { h.guardando ->  stringResource(R.string.guardando); h.edit ->  stringResource(R.string.guardar); else ->  stringResource(R.string.editar) },
                            onClick = h.onEditarGuardar
                        )
                    }
                }
            }
        }
    }
}