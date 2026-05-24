import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

suspend fun getUsuarioById(id: String): Pair<Usuario?, String?> {
    val response = supabaseClient.get("${SUPABASE_URL}/rest/v1/usuarios") {
        parameter("select", "*")
        parameter("id", "eq.$id")
        parameter("limit", "1")
    }
    val body = response.bodyAsText()
    val status = response.status.value
    println("GET USUARIO STATUS: $status BODY: $body")
    return if (status == 200) {
        val lista = Gson().fromJson(body, Array<Usuario>::class.java)
        Pair(lista.firstOrNull(), null)
    } else {
        Pair(null, "Error $status: $body")
    }
}