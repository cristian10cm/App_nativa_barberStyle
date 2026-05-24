import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AgendarViewModel : ViewModel() {
    private val _store = MutableStateFlow<Store?>(null)
    val store: StateFlow<Store?> = _store
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading
    private val _appointmentDone = MutableStateFlow(false)
    val appointmentDone: StateFlow<Boolean> = _appointmentDone
    fun loadStore(storeId: String) {
        viewModelScope.launch {
            _loading.value = true
            val (store, error) = getStoreById(storeId)
            _store.value = store  // sin cast
            _error.value = error
            _loading.value = false
        }
    }
    fun createNewAppointment(
        service: String,
        date: String,
        time: String,
        comment: String
    ) {
        val currentStore = _store.value ?: return
        viewModelScope.launch {
            _loading.value = true
            val error = createAppointment(
                storeId   = currentStore.id,
                storeName = currentStore.name,
                service   = service,
                date      = date,
                time      = time,
                address   = currentStore.address,
                comment   = comment
            )
            _error.value = error
            if (error == null) _appointmentDone.value = true
            _loading.value = false
        }
    }
}