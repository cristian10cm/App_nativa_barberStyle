data class UsuariosHook(
    val filteredUsers: List<Usuario>,
    val error: String?,
    val allTypes: List<String>,
    val selectedType: String?,
    val onTypeSelected: (String?) -> Unit,
    val onEdit: (String) -> Unit
)