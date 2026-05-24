import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

suspend fun loginUsuario(correo: String, password: String): Usuario? {
    val response = supabaseClient.get("${SUPABASE_URL}/rest/v1/usuarios") {
        parameter("select", "*")
        parameter("correo", "eq.$correo")
        parameter("password", "eq.$password")
        parameter("limit", "1")
    }
    val body = response.bodyAsText()
    val lista = Gson().fromJson(body, Array<Usuario>::class.java)
    return lista.firstOrNull()
}