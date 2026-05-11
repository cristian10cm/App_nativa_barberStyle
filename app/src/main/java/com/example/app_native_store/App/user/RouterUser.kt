import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RouterUser(){
    var navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BarUser(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "storesList",
            modifier = Modifier.padding(padding)
        ) {
            composable("account") { AccountUser() }
            composable("storesList"){Stores(navController)}

            composable("agendar/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                Agendar(id = id,navController)
            }
            composable("agenda"){
                AgendaUser()
            }
        }
    }
}