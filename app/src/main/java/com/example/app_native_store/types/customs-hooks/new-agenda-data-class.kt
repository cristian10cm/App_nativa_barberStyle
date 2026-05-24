import android.R

data class AgendarHook(
    val store: Store?,
    val loading: Boolean,
    val error: String?,
    val date: String,
    val time: String,
    val comentario: String,
    val selectedService: String,
    val onDateChange: (String) -> Unit,
    val onTimeChange: (String) -> Unit,
    val onComentarioChange: (String) -> Unit,
    val onServiceChange: (String) -> Unit,
    val onAgendar: () -> Unit,
    val dias: List<String>,
    val horas: List<String>
)