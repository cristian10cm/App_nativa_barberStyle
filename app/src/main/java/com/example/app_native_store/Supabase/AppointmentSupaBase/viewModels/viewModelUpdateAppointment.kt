import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AgendaStoreViewModel : ViewModel() {
    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _loading = MutableStateFlow(false)
    fun loadStoreAndAppointments(ownerId: String) {
        viewModelScope.launch {
            _loading.value = true
            val (store, storeErr) = getStoreByOwner(ownerId)
            if (store == null) {
                _error.value = storeErr ?: "No se encontró la tienda"
                _loading.value = false
                return@launch
            }
            val (list, err) = getAppointmentsByStore(store.id)
            _appointments.value = list ?: emptyList()
            _error.value = err
            _loading.value = false
        }
    }

    fun changeStatus(appointmentId: String, newStatus: String) {
        viewModelScope.launch {
            val err = updateAppointmentStatus(appointmentId, newStatus)
            if (err == null) {
                _appointments.value = _appointments.value.map {
                    if (it.id == appointmentId) it.copy(status = newStatus) else it
                }
            } else {
                _error.value = err
            }
        }
    }
}