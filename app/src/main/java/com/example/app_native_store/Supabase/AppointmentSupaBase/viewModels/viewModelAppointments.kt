import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AgendaViewModel : ViewModel() {
    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _loading = MutableStateFlow(false)
    fun loadAppointments(userId: String) {
        viewModelScope.launch {
            _loading.value = true
            val (list, err) = getAppointmentsByUser(userId)
            _appointments.value = list ?: emptyList()
            _error.value = err
            _loading.value = false
        }
    }

    fun removeAppointment(appointmentId: String) {
        viewModelScope.launch {
            val err = deleteAppointment(appointmentId)
            if (err == null) {
                _appointments.value = _appointments.value.filter { it.id != appointmentId }
            } else {
                _error.value = err
            }
        }
    }
    fun rateAppointment(appointmentId: String, score: Int) {
        viewModelScope.launch {
            val err = updateAppointmentScore(appointmentId, score)
            if (err == null) {
                _appointments.value = _appointments.value.map {
                    if (it.id == appointmentId) it.copy(score = score.toDouble()) else it
                }
            } else {
                _error.value = err
            }
        }
    }
}