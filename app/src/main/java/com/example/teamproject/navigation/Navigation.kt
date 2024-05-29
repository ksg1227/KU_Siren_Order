package com.example.teamproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teamproject.MenuItem
import com.example.teamproject.Restaurant.Library_GusiaScreen
import com.example.teamproject.Restaurant.StudentUnion_GusiaScreen
import com.example.teamproject.Screen.LibraryOrderScreen
import com.example.teamproject.Screen.LoginScreen
import com.example.teamproject.Screen.PaymentScreen
import com.example.teamproject.Screen.SignUpScreen
import com.example.teamproject.Screen.StartScreen
import com.example.teamproject.Screen.StudentUnionOrderScreen
import com.example.teamproject.ViewModel.LibraryMenuViewModel
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel
import com.example.teamproject.ViewModel.rememberViewModelStoreOwner

open class Routes(val route: String) {
    object Start : Routes("start_screen")
    object Login : Routes("login_screen")
    object SignUp : Routes("SignUp_screen")
    object StudentUnionGusia : Routes("studentUnion_gusiaScreen")
    object LibraryGusia : Routes("library_gusiaScreen")
    object Payment : Routes("Payment_Screen")
}


@Composable
fun NavGraph(navController: NavHostController,
             libraryViewModel: LibraryMenuViewModel = viewModel(),
             studentUnionViewModel: StudentUnionMenuViewModel = viewModel()) {

    val navStoreOwner = rememberViewModelStoreOwner()
    CompositionLocalProvider(
        LocalNavGraphViewModelStoreOwner provides navStoreOwner
    ) {
        NavHost(navController = navController, startDestination = Routes.Start.route) {

            composable(Routes.Start.route) {
                StartScreen(navController)
            }

            composable(Routes.Login.route) {
                LoginScreen(navController)
            }

            composable(Routes.SignUp.route) {
                SignUpScreen(navController)
            }

            composable(Routes.StudentUnionGusia.route) {
                StudentUnion_GusiaScreen(navController)
            }

            composable(Routes.LibraryGusia.route) {
                Library_GusiaScreen(navController)
            }

            composable(Routes.Payment.route){
                PaymentScreen(navController)
            }

            composable("library_order_screen/{category}/{index}/{imageRes}/{menuName}/{menuPrice}/{quantity}") { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
                val imageRes = backStackEntry.arguments?.getString("imageRes")?.toInt() ?: 0
                val menuName = backStackEntry.arguments?.getString("menuName") ?: ""
                val menuPrice = backStackEntry.arguments?.getString("menuPrice") ?: ""
                val quantity = backStackEntry.arguments?.getString("quantity")?.toInt() ?: 0

                val menuItem = MenuItem(imageRes, menuName, menuPrice, quantity)

                LibraryOrderScreen(
                    menuItem = menuItem,
                    category = category,
                    index = index,
                    libraryViewModel = libraryViewModel,
                    onAddToCart = {
                                  navController.navigate(Routes.LibraryGusia.route)
                                  },
                    onCheckout = {
                        // 결제 로직
                        libraryViewModel.decreaseQuantity(category, index, quantity)

                        navController.navigate(Routes.Payment.route)
                    }
                )
            }

            composable("studentUnion_order_screen/{category}/{index}/{imageRes}/{menuName}/{menuPrice}/{quantity}") { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
                val imageRes = backStackEntry.arguments?.getString("imageRes")?.toInt() ?: 0
                val menuName = backStackEntry.arguments?.getString("menuName") ?: ""
                val menuPrice = backStackEntry.arguments?.getString("menuPrice") ?: ""
                val quantity = backStackEntry.arguments?.getString("quantity")?.toInt() ?: 0

                val menuItem = MenuItem(imageRes, menuName, menuPrice, quantity)

                StudentUnionOrderScreen(
                    menuItem = menuItem,
                    category = category,
                    index = index,
                    studentUnionViewModel = studentUnionViewModel,
                    onAddToCart = {
                                  navController.navigate(Routes.LibraryGusia.route)
                                  },
                    onCheckout = {
                        // 결제 로직
                        studentUnionViewModel.decreaseQuantity(category, index, quantity)

                        navController.navigate(Routes.Payment.route)
                    }
                )
            }
        }
    }
}

