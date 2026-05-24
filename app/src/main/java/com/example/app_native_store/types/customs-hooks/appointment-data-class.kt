data class AgendaUserHook(
    val filteredAppointments: List<Appointment>,
    val error: String?,
    val allTypes: List<String>,
    val selectedType: String?,
    val onTypeSelected: (String?) -> Unit,
    val onDelete: (String) -> Unit,
    val onRate: (String, Int) -> Unit
)
