import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.util.UUID

suspend fun crearTiendaAutomatica(nombreDueno: String, ownerId: String): String? {
    val nuevaTienda = mapOf(
        "id"          to UUID.randomUUID().toString(),
        "name"        to "Tienda $nombreDueno",
        "address"     to "Por definir",
        "types"       to emptyList<String>(),
        "phone"       to null,
        "description" to "Tienda de don $nombreDueno",
        "owner_id"    to ownerId
    )
    val response = supabaseClient.post("${SUPABASE_URL}/rest/v1/stores") {
        contentType(ContentType.Application.Json)
        header("Prefer", "return=minimal")
        setBody(Gson().toJson(nuevaTienda))
    }
    val status = response.status.value
    val body   = response.bodyAsText()
    return if (status == 201 || status == 200) null else "Error al crear tienda"
}