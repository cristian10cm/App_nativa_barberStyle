import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

suspend fun getAllUsuarios(): Pair<List<Usuario>?, String?> {
    val response = supabaseClient.get("${SUPABASE_URL}/rest/v1/usuarios") {
        parameter("select", "*")
        parameter("order", "fecha_creacion.desc")
    }
    val body = response.bodyAsText()
    val status = response.status.value
    return if (status == 200) {
        val list = Gson().fromJson(body, Array<Usuario>::class.java).toList()
        Pair(list, null)
    } else {
        Pair(null, "Error $status: $body")
    }
}