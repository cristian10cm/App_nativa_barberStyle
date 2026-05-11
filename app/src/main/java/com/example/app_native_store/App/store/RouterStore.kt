import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun RouterStore(){
    var navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BarStore(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "agenda",
            modifier = Modifier.padding(padding)
        ) {
            composable("agenda") { AgendaStore() }
            composable("account"){AccountStore()}
            composable("tiendaAccount"){Tienda()}
        }
    }
}