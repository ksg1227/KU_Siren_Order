package com.example.teamproject.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.Restaurant.Library_GusiaScreen
import com.example.teamproject.Restaurant.StudentUnion_FirstfloorScreen
import com.example.teamproject.Restaurant.StudentUnion_GusiaScreen
import com.example.teamproject.Screen.CartScreen
import com.example.teamproject.Screen.Library_GusiaNoSideOrderScreen
import com.example.teamproject.Screen.Library_GusiaOrderScreen
import com.example.teamproject.Screen.LoginScreen
import com.example.teamproject.Screen.PaymentScreen
import com.example.teamproject.Screen.RestaurantLocationScreen
import com.example.teamproject.Screen.RestioLocationScreen
import com.example.teamproject.Screen.SignUpScreen
import com.example.teamproject.Screen.StartScreen
import com.example.teamproject.Screen.StudentUnion_FirstfloorOrderScreen
import com.example.teamproject.Screen.StudentUnion_GusiaNoSideOrderScreen
import com.example.teamproject.Screen.StudentUnion_GusiaOrderScreen
import com.example.teamproject.ViewModel.CartMenuViewModel
import com.example.teamproject.ViewModel.LibraryMenuViewModel
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel
import com.example.teamproject.ViewModel.rememberViewModelStoreOwner

open class Routes(val route: String) {
    object Start : Routes("start_screen")
    object Login : Routes("login_screen")
    object SignUp : Routes("SignUp_screen")
    object StudentUnionFirstfloor : Routes("StudentUnion_FirstfloorScreen")
    object StudentUnionGusia : Routes("StudentUnion_gusiaScreen")
    object LibraryGusia : Routes("library_gusiaScreen")
    object Payment : Routes("Payment_Screen")
    object RestioStart : Routes("restio_start")
    object RestaurantStart : Routes("restaurant_start")

}


@Composable
fun NavGraph(
    navController: NavHostController,
    libraryViewModel: LibraryMenuViewModel = viewModel(),
    studentUnionViewModel: StudentUnionMenuViewModel = viewModel(),
    cartViewModel: CartMenuViewModel = viewModel()
) {

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

            composable(Routes.StudentUnionFirstfloor.route) {
                StudentUnion_FirstfloorScreen(navController)
            }

            composable(Routes.LibraryGusia.route) {
                Library_GusiaScreen(navController)
            }

            composable(Routes.Payment.route) {
                PaymentScreen(navController)
            }

            composable(Routes.RestioStart.route) {
                RestioLocationScreen(navController = navController)
            }

            composable(Routes.RestaurantStart.route) {
                RestaurantLocationScreen(navController = navController)
            }

            composable("cart_screen/{placeName}") {backStackEntry ->
                val placeName = backStackEntry.arguments?.getString("placeName") ?: ""
                CartScreen(navController, placeName)
            }

            composable("library_order_screen/{category}/{index}/{imageRes}/{menuName}/{menuPrice}/{quantity}") { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
                val imageRes = backStackEntry.arguments?.getString("imageRes")?.toInt() ?: 0
                val menuName = backStackEntry.arguments?.getString("menuName") ?: ""
                val menuPrice = backStackEntry.arguments?.getString("menuPrice") ?: ""
                val quantity = backStackEntry.arguments?.getString("quantity")?.toInt() ?: 0

                val menuItem = MenuItem(imageRes, menuName, menuPrice, quantity, category, index)

                if (category == "Bab" || category == "Popo") {
                    Library_GusiaOrderScreen(
                        menuItem = menuItem,
                        category = category,
                        index = index,
                        libraryViewModel = libraryViewModel,
                        cartViewModel = cartViewModel,
                        onCheckout = {
                            // 결제 로직
                            libraryViewModel.decreaseQuantity(category, index, quantity)

                            navController.navigate(Routes.Payment.route)
                        },
                        navController = navController
                    )
                }else{
                    Library_GusiaNoSideOrderScreen(
                        menuItem = menuItem,
                        category = category,
                        index = index,
                        libraryViewModel = libraryViewModel,
                        cartViewModel = cartViewModel,
                        onCheckout = {
                            // 결제 로직
//                            libraryViewModel.decreaseQuantity(category, index, quantity)

                            navController.navigate(Routes.Payment.route)
                        },
                        navController = navController
                    )
                }

            }

            composable("studentUnion_order_screen/{category}/{index}/{imageRes}/{menuName}/{menuPrice}/{quantity}") { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toInt() ?: 0
                val imageRes = backStackEntry.arguments?.getString("imageRes")?.toInt() ?: 0
                val menuName = backStackEntry.arguments?.getString("menuName") ?: ""
                val menuPrice = backStackEntry.arguments?.getString("menuPrice") ?: ""
                val quantity = backStackEntry.arguments?.getString("quantity")?.toInt() ?: 0

                val menuItem = MenuItem(imageRes, menuName, menuPrice, quantity, category, index)

                if (category == "Firstfloor") {
                    StudentUnion_FirstfloorOrderScreen(
                        menuItem = menuItem,
                        category = category,
                        index = index,
                        studentUnionViewModel = studentUnionViewModel,
                        cartViewModel = cartViewModel,
                        onCheckout = {
                            // 결제 로직
//                            studentUnionViewModel.decreaseQuantity(category, index, quantity)

                            navController.navigate(Routes.Payment.route)
                        },
                        navController = navController
                    )
                } else {
                    if (category == "Bab" || category == "Popo" || category == "Gookbab" || category == "Mara") {
                        StudentUnion_GusiaOrderScreen(
                            menuItem = menuItem,
                            category = category,
                            index = index,
                            studentUnionViewModel = studentUnionViewModel,
                            cartViewModel = cartViewModel,
                            onCheckout = {
                                // 결제 로직
//                                studentUnionViewModel.decreaseQuantity(category, index, quantity)

                                navController.navigate(Routes.Payment.route)
                            },
                            navController = navController
                        )
                    } else {
                        StudentUnion_GusiaNoSideOrderScreen(
                            menuItem = menuItem,
                            category = category,
                            index = index,
                            studentUnionViewModel = studentUnionViewModel,
                            cartViewModel = cartViewModel,
                            onCheckout = {
                                // 결제 로직
//                                studentUnionViewModel.decreaseQuantity(category, index, quantity)

                                navController.navigate(Routes.Payment.route)
                            },
                            navController = navController
                        )
                    }
                }

            }
        }
    }
}

