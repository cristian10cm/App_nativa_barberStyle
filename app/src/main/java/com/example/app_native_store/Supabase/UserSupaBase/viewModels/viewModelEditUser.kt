import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditUserViewModel : ViewModel() {
    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading
    private val _saved = MutableStateFlow(false)
    val saved: StateFlow<Boolean> = _saved
    fun loadUsuario(id: String) {
        viewModelScope.launch {
            _loading.value = true
            val (user, err) = getUsuarioById(id)
            _usuario.value = user
            _error.value = err
            _loading.value = false
        }
    }
    fun guardar(
        id: String,
        nombre: String,
        telefono: String,
        ciudad: String,
        nuevaClave: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            val err = updateUsuario(id, nombre, telefono, ciudad)
            if (err == null && nuevaClave.isNotBlank()) {
                updatePassword(id, nuevaClave)
            }
            _error.value = err
            if (err == null) _saved.value = true
            _loading.value = false
        }
    }
}