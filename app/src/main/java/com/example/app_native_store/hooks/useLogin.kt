import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.app_native_store.network.UserSesion
import kotlinx.coroutines.launch

@Composable
fun useLogin(
    onLoginSuccess: (rol: String, email: String) -> Unit,
    snackbarHostState: SnackbarHostState
): LoginHook {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    return LoginHook(
        email = email,
        password = password,
        cargando = cargando,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onLogin = {
            if (cargando) return@LoginHook
            scope.launch {
                val errorMessage = when {
                    email.isBlank() -> "El correo es obligatorio"
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Correo inválido"
                    password.isBlank() -> "La contraseña es obligatoria"
                    else -> null
                }

                if (errorMessage != null) {
                    snackbarHostState.showSnackbar(errorMessage)
                    return@launch
                }

                cargando = true
                try {
                    val usuario = loginUsuario(email.trim(), password)
                    if (usuario == null) {
                        snackbarHostState.showSnackbar("Correo o contraseña incorrectos")
                    } else {
                        UserSesion.login(usuario)
                        snackbarHostState.showSnackbar("Bienvenido ${usuario.nombre}")
                        when (usuario.rol) {
                            "dueno_tienda", "tienda" -> onLoginSuccess("tienda", usuario.correo)
                            "admin"                  -> onLoginSuccess("admin",  usuario.correo)
                            else                     -> onLoginSuccess("user",   usuario.correo)
                        }
                    }
                } catch (e: Exception) {
                    snackbarHostState.showSnackbar("Error al iniciar sesion")
                } finally {
                    cargando = false
                }
            }
        }
    )
}