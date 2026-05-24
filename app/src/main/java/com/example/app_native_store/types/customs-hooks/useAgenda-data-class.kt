data class AgendaStoreHook(
    val appointments: List<Appointment>,
    val error: String?,
    val allTypes: List<String>,
    val filteredAppointments: List<Appointment>,
    val selectedType: String?,
    val onTypeSelected: (String?) -> Unit,
    val changeStatus: (String, String) -> Unit
)