data class LoginHook(
    val email: String,
    val password: String,
    val cargando: Boolean,
    val onEmailChange: (String) -> Unit,
    val onPasswordChange: (String) -> Unit,
    val onLogin: () -> Unit
)