import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_native_store.R


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
            startDestination =  stringResource(R.string.usuarios),
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