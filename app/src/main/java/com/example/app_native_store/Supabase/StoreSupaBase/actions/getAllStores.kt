import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

suspend fun getAllStores(): Pair<List<Store>?, String?> {
    val response = supabaseClient.get("${SUPABASE_URL}/rest/v1/stores") {
        parameter("select", "*")
    }

    val body = response.bodyAsText()
    val status = response.status.value

    return if (status == 200) {
        val list = Gson().fromJson(body, Array<Store>::class.java).toList()
        Pair(list, null)
    } else {
        Pair(null, body)
    }
}