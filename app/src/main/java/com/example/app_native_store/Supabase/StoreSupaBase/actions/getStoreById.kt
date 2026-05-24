import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

suspend fun getStoreById(storeId: String): Pair<Store?, String?> {
    val response = supabaseClient.get("${SUPABASE_URL}/rest/v1/stores") {
        parameter("select", "*")
        parameter("id", "eq.$storeId")
        parameter("limit", "1")
    }

    val body = response.bodyAsText()
    val status = response.status.value

    return if (status == 200) {
        val list = Gson().fromJson(body, Array<Store>::class.java)
        Pair(list.firstOrNull(), null)
    } else {
        Pair(null, "Error")
    }
}