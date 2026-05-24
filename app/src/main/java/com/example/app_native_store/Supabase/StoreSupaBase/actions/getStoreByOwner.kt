import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

suspend fun getStoreByOwner(ownerId: String): Pair<Store?, String?> {
    val response = supabaseClient.get("${SUPABASE_URL}/rest/v1/stores") {
        parameter("select", "*")
        parameter("owner_id", "eq.$ownerId")
        parameter("limit", "1")
    }
    val body = response.bodyAsText()
    val status = response.status.value

    println("STATUS: $status")
    println("BODY: $body")

    return if (status == 200) {
        val lista = Gson().fromJson(body, Array<Store>::class.java)
        println("LISTA SIZE: ${lista.size}")
        Pair(lista.firstOrNull(), null)
    } else {
        Pair(null, "Error $status: $body")
    }
}