import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable

fun BarSuperAdmin(navController: NavController){
    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("usuarios") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Checklist,
                    contentDescription = "services"
                )
            },
            label = { Text("Usuarios") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("login") },
            icon = {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Salir"
                )
            },
            label = { Text("Salir") }
        )

    }

}