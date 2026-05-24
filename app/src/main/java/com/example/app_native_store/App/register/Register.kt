package com.example.app_native_store.ui

import ButtonComponent
import InputComponent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_native_store.R
import useRegister

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUser(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val h = useRegister(navController, snackbarHostState)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState, modifier = Modifier.padding(top = 20.dp)) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(R.string.crear_cuenta), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(7.dp))
                    .padding(10.dp)
            ) {
                InputComponent(value = h.nombre,            onChange = h.onNombreChange,            type = "text",     label = stringResource(
                    R.string.nombre_completo
                ))
                InputComponent(value = h.correo,            onChange = h.onCorreoChange,            type = "email",    label = stringResource(R.string.correo))
                InputComponent(value = h.password,          onChange = h.onPasswordChange,          type = "password", label = stringResource(
                    R.string.contrasena
                ))
                InputComponent(value = h.confirmarPassword, onChange = h.onConfirmarPasswordChange, type = "password", label = stringResource(
                    R.string.confirmar_contrase_a
                ))

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(R.string.tipo_de_cuenta), fontSize = 13.sp, color = Color.Gray, modifier = Modifier.padding(start = 4.dp, bottom = 4.dp))

                ExposedDropdownMenuBox(
                    expanded = h.dropdownExpanded,
                    onExpandedChange = { h.onDropdownToggle() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = h.rolSeleccionado.second,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = h.dropdownExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(7.dp)
                    )
                    ExposedDropdownMenu(expanded = h.dropdownExpanded, onDismissRequest = h.onDropdownDismiss) {
                        h.roles.forEach { rol ->
                            DropdownMenuItem(text = { Text(rol.second) }, onClick = { h.onRolSelected(rol) })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                InputComponent(value = h.telefono,          onChange = h.onTelefonoChange,   type = "text",   label = stringResource(R.string.tel_fono))
                InputComponent(value = h.genero,            onChange = h.onGeneroChange,     type = "text",   label = stringResource(
                    R.string.g_nero
                ))
                InputComponent(value = h.edad,              onChange = h.onEdadChange,       type = "number", label = stringResource(
                    R.string.edad
                ))
                InputComponent(value = h.ciudad,            onChange = h.onCiudadChange,     type = "text",   label = stringResource(
                    R.string.ciudad
                ))
                InputComponent(value = h.descripcionPerfil, onChange = h.onDescripcionChange,type = "text",   label = stringResource(
                    R.string.descripci_n_de_perfil
                ))
                Spacer(modifier = Modifier.height(16.dp))
                ButtonComponent(textBtn = if (h.cargando) stringResource(R.string.guardando) else stringResource(R.string.crear_cuenta), onClick = h.onRegistrar)
                Spacer(modifier = Modifier.height(12.dp))
                ButtonComponent(textBtn = stringResource(R.string.ya_tengo_cuenta), onClick = { navController.popBackStack() })
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}