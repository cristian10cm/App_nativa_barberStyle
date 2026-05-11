import android.content.ClipData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.app_native_store.ui.components.FilterChips

@Composable

fun Usuarios(navController: NavController){
    var selectedType by remember { mutableStateOf<String?>(null) }
    val allTypes = listOf(
        "Usuarios", "Tiendas","Todos"
    )
    val filteredUser = if (selectedType == null || selectedType == "Todos") {
        UserList
    } else {
        if(selectedType == "Usuarios"){
            UserList.filter { it.rol == "user" }
        }else{
            UserList.filter { it.rol == "store" }
        }

    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header("Agenda de citas")

        FilterChips(
            types = allTypes,
            selectedType = selectedType,
            onTypeSelected = { selectedType = it }
        )
        LazyColumn() {
                items(filteredUser){
                    app->
                        UserComp(
                            user = app,
                            onEdit =  { id ->
                                navController.navigate("verUsuarios/$id")
                            }
                        )
                }
        }

    }
}