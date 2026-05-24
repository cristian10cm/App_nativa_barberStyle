import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.app_native_store.R
import com.example.app_native_store.ui.components.FilterChips

@Composable
fun Usuarios(navController: NavController) {
    val h = useUsuarios(navController)

    Column(modifier = Modifier.fillMaxSize()) {
        Header(stringResource(R.string.usuarios))

        h.error?.let { Text(text = it, color = Color.Red) }

        FilterChips(
            types = h.allTypes,
            selectedType = h.selectedType,
            onTypeSelected = h.onTypeSelected
        )

        LazyColumn {
            items(h.filteredUsers) { user ->
                UserComp(user = user, onEdit = h.onEdit)
            }
        }
    }
}