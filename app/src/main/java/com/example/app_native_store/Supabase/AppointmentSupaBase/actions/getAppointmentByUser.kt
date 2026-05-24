import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText

suspend fun getAppointmentsByUser(userId: String): Pair<List<Appointment>?, String?> {


    val response = supabaseClient.get("$SUPABASE_URL/rest/v1/appointments") {
        header("user_id", "eq.$userId")
    }
    val body = response.bodyAsText()
    val status = response.status.value


    return if (status == 200) {
        val list = Gson().fromJson(body, Array<Appointment>::class.java).toList()
        Pair(list, null)
    } else {
        Pair(null, "Error")
    }
}