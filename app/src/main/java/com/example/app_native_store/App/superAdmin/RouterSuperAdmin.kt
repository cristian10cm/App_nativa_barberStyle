import android.app.FragmentManager
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun RouterSuperAdmin(){
    var navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BarSuperAdmin(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "usuarios",
            modifier = Modifier.padding(padding)
        ) {
            composable("usuarios"){
                Usuarios(navController)
            }
            composable("verUsuarios/{id}"){
                backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")
                    EditUser(id)
            }
        }
    }
}