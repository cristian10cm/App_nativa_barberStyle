import com.example.app_native_store.data.appointmentsList

fun NewScore(id: String, newScore: Number) {

    val index = appointmentsList.indexOfFirst { it.id == id }

    if (index != -1) {
        val updated = appointmentsList[index].copy(
            score = newScore
        )

        appointmentsList[index] = updated
    }
}