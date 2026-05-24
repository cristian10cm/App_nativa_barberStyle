package com.example.app_native_store.ui

import ButtonComponent
import Header
import SelectComp
import StoreCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_native_store.R
import useAgendar



@Composable
fun Agendar(id: String?, navController: NavController) {
    val h = useAgendar(id, navController)

    Column(modifier = Modifier.fillMaxSize()) {
        Header(name = stringResource(R.string.agendar_cita))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            h.error?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            h.store?.let {
                StoreCard(it)
                SelectComp(
                    label = stringResource(R.string.servicio),
                    options = it.types,
                    onValueChange = h.onServiceChange
                )
            }

            SelectComp(
                label = stringResource(R.string.fecha),
                options = h.dias,
                onValueChange = h.onDateChange
            )

            SelectComp(
                label = stringResource(R.string.hora),
                options = h.horas,
                onValueChange = h.onTimeChange
            )

            OutlinedTextField(
                value = h.comentario,
                onValueChange = h.onComentarioChange,
                label = { Text(stringResource(R.string.comentario)) },
                modifier = Modifier.fillMaxWidth()
            )

            ButtonComponent(
                onClick = h.onAgendar,
                textBtn = if (h.loading)
                    stringResource(R.string.guardando)
                else
                    stringResource(R.string.agendar_cita)
            )
        }
    }
}