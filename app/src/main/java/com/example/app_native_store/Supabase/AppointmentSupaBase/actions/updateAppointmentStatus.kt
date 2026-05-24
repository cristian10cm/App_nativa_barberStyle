import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend fun updateAppointmentStatus(appointmentId: String, newStatus: String): String? {
    val datos = mapOf("status" to newStatus)

    val response = supabaseClient.patch("$SUPABASE_URL/rest/v1/appointments") {
        contentType(ContentType.Application.Json)
        header("Prefer", "return=minimal")
        parameter("id", "eq.$appointmentId")
        setBody(Gson().toJson(datos))
    }

    val status = response.status.value
    val body = response.bodyAsText()

    return if (status in 200..204) null
    else "Error al cambiar estado de la cita"
}