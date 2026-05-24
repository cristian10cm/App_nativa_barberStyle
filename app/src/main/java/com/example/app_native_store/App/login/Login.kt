package com.example.app_native_store.ui

import ButtonComponent
import InputComponent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_native_store.R
import useLogin
@Composable
fun Login(
    onLogin: (rol: String, email: String) -> Unit,
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val hook = useLogin(onLoginSuccess = onLogin, snackbarHostState = snackbarHostState)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState, modifier = Modifier.padding(top = 20.dp)) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(15.dp)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo_app_native), contentDescription = "Logo", modifier = Modifier.size(200.dp))
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = stringResource(R.string.iniciar_sesi_n), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(7.dp))
                    .padding(10.dp)
            ) {
                InputComponent(value = hook.email, onChange = hook.onEmailChange, type = "email", label = stringResource(
                    R.string.ingresa_tu_correo
                ))
                InputComponent(value = hook.password, onChange = hook.onPasswordChange, type = "password", label = stringResource(R.string.contrase_a))
                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    ButtonComponent(
                        textBtn = if (hook.cargando) stringResource(R.string.ingresando) else stringResource(R.string.ingresar_login),
                        onClick = hook.onLogin
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.border(width = 0.dp, color = Color.Transparent),
                        onClick = { navController.navigate("RegisterUser") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(5.dp)
                    ) { Text(text = stringResource(R.string.registrarse), color = Color.Black) }

                    Button(
                        modifier = Modifier.border(width = 0.dp, color = Color.Transparent),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(5.dp)
                    ) { Text(text = stringResource(R.string.olvid_contrase_a), color = Color.Black) }
                }
            }
        }
    }
}