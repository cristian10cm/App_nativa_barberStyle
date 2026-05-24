import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class StoreViewModel : ViewModel() {
    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores
    init {
        loadStores()
    }
    fun loadStores() {
        viewModelScope.launch {
            val (data, error) = getAllStores()

            if (error == null && data != null) {
                _stores.value = data
            } else {
                println("Error cargando stores")
            }
        }
    }
}