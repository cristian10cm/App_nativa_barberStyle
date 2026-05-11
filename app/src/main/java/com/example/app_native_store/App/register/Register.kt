import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_native_store.R
import kotlinx.coroutines.launch

@Composable
fun RegisterUser(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var descripcionPerfil by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var genero by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("") }
    val ciudadesBogota = remember {
        mutableStateListOf(
            "Usaquén",
            "Chapinero",
            "Santa Fe",
            "San Cristóbal",
            "Usme",
            "Tunjuelito",
            "Bosa",
            "Kennedy",
            "Fontibón",
            "Engativá",
            "Suba",
            "Barrios Unidos",
            "Teusaquillo",
            "Los Mártires",
            "Antonio Nariño",
            "Puente Aranda",
            "La Candelaria",
            "Rafael Uribe Uribe",
            "Ciudad Bolívar",
            "Sumapaz"
        )
    }
    val generos = remember {
        mutableStateListOf(
            "Masculino",
            "Femenino"
        )
    }

    val roles = remember {
        mutableStateListOf(
            "Dueño de tienda",
            "Cliente"
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Registro de Usuario",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .border(
                        1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(7.dp)
                    )
                    .padding(12.dp)
            ) {

                InputComponent(
                    value = nombre,
                    onChange = { nombre = it },
                    type = "text",
                    label = "Nombre completo"
                )

                InputComponent(
                    value = telefono,
                    onChange = { telefono = it },
                    type = "number",
                    label = "Teléfono"
                )

                SelectComp(
                    label = "Género",
                    options = generos,
                    onValueChange = {
                        genero = it
                    }
                )

                InputComponent(
                    value = edad,
                    onChange = { edad = it },
                    type = "number",
                    label = "Edad"
                )

                InputComponent(
                    value = correo,
                    onChange = { correo = it },
                    type = "email",
                    label = "Correo electrónico"
                )

                InputComponent(
                    value = descripcionPerfil,
                    onChange = { descripcionPerfil = it },
                    type = "text",
                    label = "Descripción del perfil"
                )
                SelectComp(
                    label = "Rol",
                    options = roles,
                    onValueChange = {
                        rol = it
                    }
                )

                SelectComp(
                    label = "Localidad",
                    options = ciudadesBogota,
                    onValueChange = {
                        ciudad = it
                    }
                )


                InputComponent(
                    value = password,
                    onChange = { password = it },
                    type = "password",
                    label = "Contraseña"
                )



                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    ButtonComponent(
                        textBtn = "Registrarse",
                        onClick = {

                            scope.launch {

                                val errorMessage = when {

                                    nombre.isBlank() ->
                                        "El nombre es obligatorio"

                                    telefono.isBlank() ->
                                        "El teléfono es obligatorio"

                                    telefono.length != 10 ->
                                        "El teléfono debe tener 10 números"

                                    genero.isBlank() ->
                                        "Selecciona un género"

                                    edad.isBlank() ->
                                        "La edad es obligatoria"

                                    edad.toIntOrNull() == null ->
                                        "La edad debe ser numérica"

                                    edad.toInt() < 18 ->
                                        "Debes ser mayor de edad"

                                    correo.isBlank() ->
                                        "El correo es obligatorio"

                                    !android.util.Patterns.EMAIL_ADDRESS
                                        .matcher(correo)
                                        .matches() ->
                                        "Correo inválido"

                                    UserList.any { it.correo == correo } ->
                                        "El correo ya está registrado"

                                    descripcionPerfil.isBlank() ->
                                        "La descripción es obligatoria"

                                    ciudad.isBlank() ->
                                        "Selecciona una localidad"

                                    password.isBlank() ->
                                        "La contraseña es obligatoria"

                                    password.length < 6 ->
                                        "La contraseña debe tener mínimo 6 caracteres"

                                    rol.isBlank() ->
                                        "Selecciona un rol"

                                    else -> null
                                }

                                if (errorMessage != null) {

                                    snackbarHostState.showSnackbar(errorMessage)

                                } else {

                                    val newUser = UsuarioType(
                                        nombre = nombre,
                                        telefono = telefono,
                                        genero = genero,
                                        edad = edad.toInt(),
                                        correo = correo,
                                        descripcionPerfil = descripcionPerfil,
                                        ciudad = ciudad,
                                        fechaCreacion = "2026-05-10",
                                        password = password,
                                        rol = if (rol == "Dueño de tienda") "store" else "user",
                                        id = (UserList.size + 1).toString()
                                    )

                                    UserList.add(newUser)

                                    snackbarHostState.showSnackbar(
                                        "Usuario registrado correctamente"
                                    )

                                    nombre = ""
                                    telefono = ""
                                    genero = ""
                                    edad = ""
                                    correo = ""
                                    descripcionPerfil = ""
                                    ciudad = ""
                                    password = ""
                                    rol = ""

                                    navController.navigate("login")
                                }
                            }
                        }
                    )
                }
            }
        }
    }

}