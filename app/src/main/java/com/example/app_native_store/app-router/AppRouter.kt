import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            Login(
                onLogin  = {
                    role,email->
                    if(role == "tienda"){
                        navController.navigate("tienda")

                    }else if(role == "admin"){
                        navController.navigate("admin")
                    }
                    else{
                        navController.navigate("user")
                    }
                },
                navController
            )
        }
        composable("user"){
            RouterUser()
        }
        composable("tienda"){
            RouterStore()
        }
        composable("admin"){
            RouterSuperAdmin()
        }
        composable("RegisterUser"){
            RegisterUser(navController)
        }




    }

}