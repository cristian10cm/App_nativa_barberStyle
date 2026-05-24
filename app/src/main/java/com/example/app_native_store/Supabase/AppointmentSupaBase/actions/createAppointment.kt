import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.UserSesion
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend fun createAppointment(
    storeId: String,
    storeName: String,
    service: String,
    date: String,
    time: String,
    address: String,
    comment: String
): String? {
    val datos = mapOf(
        "store_id"   to storeId,
        "user_id"    to UserSesion.usuario?.id,
        "service"    to service,
        "date"       to date,
        "time"       to time,
        "status"     to "pendiente",
        "address"    to address,
        "comment"    to comment
    )

    val response = supabaseClient.post("${SUPABASE_URL}/rest/v1/appointments") {
        contentType(ContentType.Application.Json)
        header("Prefer", "return=minimal")
        setBody(Gson().toJson(datos))
    }

    val status = response.status.value
    val body   = response.bodyAsText()

    return if (status in 200..204) null
    else "Error $status: $body"
}