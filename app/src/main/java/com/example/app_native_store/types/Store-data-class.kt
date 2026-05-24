import com.google.gson.annotations.SerializedName

data class Store(
    val id: String = "",
    val name: String = "",
    val address: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    val types: List<String> = emptyList(),
    val phone: String? = null,
    val description: String? = null,
    @SerializedName("owner_id")
    val ownerId: String = ""
)