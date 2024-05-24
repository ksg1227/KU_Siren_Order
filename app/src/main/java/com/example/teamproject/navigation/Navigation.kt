package com.example.teamproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teamproject.Restaurant.Library_GusiaScreen
import com.example.teamproject.Restaurant.StudentUnion_GusiaScreen
import com.example.teamproject.Screen.LoginScreen
import com.example.teamproject.Screen.StartScreen

open class Routes(val route: String) {
    object Start : Routes("start_screen")
    object Login : Routes("login_screen")
    object StudentUnionGusia : Routes("studentUnion_gusiaScreen")
    object LibraryGusia : Routes("library_gusiaScreen")
}

@Composable
fun NavGraph(navController: NavHostController) {

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
    }
}

