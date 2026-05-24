import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

suspend fun deleteAppointment(appointmentId: String): String? {
    val response = supabaseClient.delete("$SUPABASE_URL/rest/v1/appointments") {
        parameter("id", "eq.$appointmentId")
    }

    val status = response.status.value
    val body = response.bodyAsText()

    return if (status in 200..204) null
    else "Error"
}