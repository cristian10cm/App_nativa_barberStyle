import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuariosViewModel : ViewModel() {
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading
    fun loadUsuarios() {
        viewModelScope.launch {
            _loading.value = true
            val (list, err) = getAllUsuarios()
            _usuarios.value = list ?: emptyList()
            _error.value = err
            _loading.value = false
        }
    }
}