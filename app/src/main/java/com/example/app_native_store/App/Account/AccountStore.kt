package com.example.app_native_store.ui

import ButtonComponent
import Header
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.sp
import com.example.app_native_store.R
import useAccountStore


@Composable
fun Account() {
    val snackbarHostState = remember { SnackbarHostState() }
    val h = useAccountStore(snackbarHostState)

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        when {
            h.cargando -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            h.errorMsg != null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(h.errorMsg ?: "", color = Color.Red)
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Header(name = stringResource(R.string.cuenta))

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                        .padding(15.dp)) {
                        Text(stringResource(R.string.informaci_n_sobre_la_cuenta), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(value = h.nombre, onValueChange = h.onNombreChange, label = { Text(
                            stringResource(R.string.nombre)
                        ) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = h.telefono, onValueChange = h.onTelefonoChange, label = { Text(
                            stringResource(R.string.tel_fono)
                        ) }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = h.ciudad, onValueChange = h.onCiudadChange, label = { Text("Ciudad") }, enabled = h.edit, singleLine = true, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(
                            value = when (h.usuario?.rol) {
                                stringResource(R.string.dueno_tienda) -> stringResource(R.string.due_o_de_tienda)
                                ; "admin" -> "Administrador"; else -> stringResource(
                                R.string.usuario
                            )
                            },
                            onValueChange = {}, label = { Text("Rol") }, enabled = false, singleLine = true, modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(value = h.usuario?.fechaCreacion ?: "", onValueChange = {}, label = { Text(
                            stringResource(R.string.fecha_de_creaci_n)
                        ) }, enabled = false, singleLine = true, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = h.usuario?.correo ?: "", onValueChange = {}, label = { Text(
                            stringResource(R.string.correo)
                        ) }, enabled = false, singleLine = true, modifier = Modifier.fillMaxWidth())

                        Spacer(modifier = Modifier.height(12.dp))
                        ButtonComponent(
                            textBtn = when { h.guardando -> stringResource(R.string.guardando)
                                ; h.edit -> stringResource(R.string.guardar)
                                ; else -> stringResource(R.string.editar)
                            },
                            onClick = h.onEditarGuardar
                        )
                    }

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                        .padding(15.dp)) {
                        Text(stringResource(R.string.contrase_a), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(15.dp))

                        if (h.editClave) {
                            OutlinedTextField(value = h.claveActual, onValueChange = h.onClaveActualChange, label = { Text(
                                stringResource(R.string.contrase_a_actual)
                            ) }, singleLine = true, modifier = Modifier.fillMaxWidth())
                            OutlinedTextField(value = h.nuevaClave, onValueChange = h.onNuevaClaveChange, label = { Text(
                                stringResource(R.string.nueva_contrase_a)
                            ) }, singleLine = true, modifier = Modifier.fillMaxWidth())
                            if (h.claveIncorrecta) {
                                Text(text = stringResource(R.string.la_contrase_a_actual_no_es_correcta), color = Color.Red, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        ButtonComponent(
                            textBtn = when { h.guardandoClave -> stringResource(R.string.guardando); h.editClave -> stringResource(
                                R.string.confirmar_cambio
                            )
                                ; else -> stringResource(R.string.cambiar_contrase_a)

                            },
                            onClick = h.onCambiarClave
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}