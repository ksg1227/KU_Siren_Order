package com.example.teamproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teamproject.Restaurant.Library_GusiaScreen
import com.example.teamproject.Restaurant.StudentUnion_GusiaScreen
import com.example.teamproject.Screen.LoginScreen
import com.example.teamproject.Screen.MapScreen
import com.example.teamproject.Screen.StartScreen
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.ViewModel.rememberViewModelStoreOwner

open class Routes(val route: String) {
    object Start : Routes("start_screen")
    object Login : Routes("login_screen")
    object StudentUnionGusia : Routes("studentUnion_gusiaScreen")
    object LibraryGusia : Routes("library_gusiaScreen")
    object Map : Routes("map_screen")
}

@Composable
fun NavGraph(navController: NavHostController) {

    val navStoreOwner = rememberViewModelStoreOwner()
    CompositionLocalProvider(
        LocalNavGraphViewModelStoreOwner provides navStoreOwner
    ) {
        NavHost(navController = navController, startDestination = "start_screen") {

            composable(Routes.Start.route) {
                StartScreen(navController)
            }

            composable(Routes.Login.route) {
                LoginScreen(navController)
            }

            composable(Routes.StudentUnionGusia.route) {
                StudentUnion_GusiaScreen(navController)
            }

            composable(Routes.LibraryGusia.route) {
                Library_GusiaScreen(navController)
            }

            composable(Routes.Map.route) {
                MapScreen(navController)
            }
        }
    }
}

