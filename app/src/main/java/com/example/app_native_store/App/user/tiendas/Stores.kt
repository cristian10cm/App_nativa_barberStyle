import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.app_native_store.ui.components.FilterChips
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun Stores(navController: NavController){
    var selectedType by remember { mutableStateOf<String?>(null) }

    val allTypes = listOf(
        "uñas", "barberia", "pestañas", "cejas", "cortes para dama"
    )

    val filteredStores = if (selectedType == null) {
        storeList
    } else {
        storeList.filter { store ->
            store.types.contains(selectedType)
        }
    }

    Column {

        Header("Tiendas")

        FilterChips(
            types = allTypes,
            selectedType = selectedType,
            onTypeSelected = { selectedType = it }
        )

        LazyColumn {
            items(filteredStores) { store ->
                StoreComp(
                    store = store,
                    onClick = { id ->
                        navController.navigate("agendar/$id")
                    }
                )
            }
        }
    }
}