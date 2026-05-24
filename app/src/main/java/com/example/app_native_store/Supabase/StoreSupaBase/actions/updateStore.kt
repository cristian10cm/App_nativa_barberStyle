import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend fun updateStore(storeId: String, store: Store): String? {
    val datos = mapOf(
        "name"        to store.name,
        "address"     to store.address,
        "phone"       to store.phone?.ifBlank { null },
        "description" to store.description?.ifBlank { null },
        "types"       to store.types
    )

    val response = supabaseClient.patch("${SUPABASE_URL}/rest/v1/stores") {
        contentType(ContentType.Application.Json)
        header("Prefer", "return=minimal")
        parameter("id", "eq.$storeId")
        setBody(Gson().toJson(datos))
    }

    val status = response.status.value

    return if (status in 200..204) null
    else "Error al actualizar tienda"
}