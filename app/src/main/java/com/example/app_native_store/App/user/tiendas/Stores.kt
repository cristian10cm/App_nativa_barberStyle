import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.app_native_store.ui.components.FilterChips
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.app_native_store.R

@Composable
fun Stores(
    navController: NavController,
    viewModel: StoreViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val stores by viewModel.stores.collectAsState()

    var selectedType by remember { mutableStateOf<String?>(null) }

    val allTypes = listOf(
        stringResource(R.string.u_as),  stringResource(R.string.barberia),  stringResource(R.string.pesta_as),  stringResource(R.string.cejas),  stringResource(R.string.cortes_para_dama)
    )

    val filteredStores = if (selectedType == null) {
        stores
    } else {
        stores.filter { it.types.contains(selectedType) }
    }

    Column {

        Header(stringResource(R.string.tiendas))

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