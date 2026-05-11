data class StoreType(
    val id: Int,
    val name: String,
    val address: String,
    val createdAt: String,
    val photo: String,
    val types: List<String>,
    val phone: String,
    val description: String,
    val ownerId: String
)