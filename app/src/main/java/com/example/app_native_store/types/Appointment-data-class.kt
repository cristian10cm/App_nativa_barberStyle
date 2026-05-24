import com.google.gson.annotations.SerializedName

data class Appointment(
    val id: String = "",
    @SerializedName("store_id")
    val storeId: String = "",
    val service: String = "",
    @SerializedName("user_id")
    val userId: String = "",
    val date: String = "",
    val time: String = "",
    val status: String = "",
    val address: String = "",
    val comment: String? = null,
    val score: Double? = null,
    @SerializedName("created_at")
    val createdAt: String = ""
)