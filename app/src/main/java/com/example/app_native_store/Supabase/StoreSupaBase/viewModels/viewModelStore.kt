import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class StoreDetailViewModel : ViewModel() {

    private val _store = MutableStateFlow<Store?>(null)
    val store: StateFlow<Store?> = _store

    fun loadStore(id: String) {
        viewModelScope.launch {
            val (data, error) = getStoreById(id)

            if (error == null) {
                _store.value = data
            } else {
                println("Error: $error")
            }
        }
    }
}