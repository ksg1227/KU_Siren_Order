package com.example.teamproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.MyPage.MyPageShowScreen
import com.example.teamproject.Restaurant.Library_GusiaScreen
import com.example.teamproject.Restaurant.StudentUnion_GusiaScreen
import com.example.teamproject.Restio.AnimalLifeRestioScreen
import com.example.teamproject.Restio.IndustryRestioScreen
import com.example.teamproject.Restio.LibraryRestioScreen
import com.example.teamproject.Restio.ManagementRestioScreen
import com.example.teamproject.Screen.CafeteriaRestioSelScreen
import com.example.teamproject.Screen.LibraryOrderScreen
import com.example.teamproject.Screen.LoginScreen
import com.example.teamproject.Screen.PaymentScreen
import com.example.teamproject.Screen.RestaurantLocationScreen
import com.example.teamproject.Screen.RestioLocationScreen
import com.example.teamproject.Screen.SignUpScreen
import com.example.teamproject.Screen.StartScreen
import com.example.teamproject.Screen.StudentUnionOrderScreen
import com.example.teamproject.ViewModel.LibraryMenuViewModel
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel
import com.example.teamproject.ViewModel.rememberViewModelStoreOwner
import com.example.teamproject.mypage.MyPageEditScreen
import com.example.teamproject.mypage.MyPageMainScreen

open class Routes(val route: String) {
    object Start : Routes("start_screen")
    object Login : Routes("login_screen")
    object SignUp : Routes("SignUp_screen")
    object StudentUnionGusia : Routes("studentUnion_gusiaScreen")
    object LibraryGusia : Routes("library_gusiaScreen")

    object Payment : Routes("Payment_Screen")
    object RestioStart : Routes("restio_start")
    object RestaurantStart : Routes("restaurant_start")

    object EatRestioSel : Routes("cafeteria_restio_sel")
    object EngineeringRestio : Routes("engineering_restio")
    object AnimalLifeRestio : Routes("animallife_restio")
    object LibraryRestio : Routes("library_restio")
    object ManagementRestio : Routes("management_restio")
    object IndustryResio : Routes("industry_restio")
    object MyPageMain : Routes("mypage_main")
    object MyPageEdit : Routes("mypage_edit")
    object MyPageShow : Routes("mypage_show")
}


@Composable
fun NavGraph(
    navController: NavHostController,
    libraryViewModel: LibraryMenuViewModel = viewModel(),
    studentUnionViewModel: StudentUnionMenuViewModel = viewModel()
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

            composable(Routes.EatRestioSel.route) {
                CafeteriaRestioSelScreen(navController)
            }

            composable(Routes.StudentUnionGusia.route) {
                StudentUnion_GusiaScreen(navController)
            }

            composable(Routes.LibraryGusia.route) {
                Library_GusiaScreen(navController)
            }

            composable(Routes.Payment.route) {
                PaymentScreen(navController)
            }

            composable (Routes.RestioStart.route){
                RestioLocationScreen(navController = navController)
            }

            composable (Routes.RestaurantStart.route){
                RestaurantLocationScreen(navController = navController)
            }

            composable (Routes.EngineeringRestio.route){
                com.example.teamproject.Restio.EngineeringRestioScreen(navController = navController)
            }

            composable (Routes.AnimalLifeRestio.route) {
                AnimalLifeRestioScreen(navController = navController)
            }

            composable (Routes.LibraryRestio.route) {
                LibraryRestioScreen(navController)
            }

            composable (Routes.ManagementRestio.route) {
                ManagementRestioScreen(navController)
            }

            composable (Routes.IndustryResio.route) {
                IndustryRestioScreen(navController)
            }

            composable (Routes.MyPageMain.route) {
                MyPageMainScreen(navController)
            }

            composable (Routes.MyPageShow.route) {
                MyPageShowScreen(navController)
            }

            composable (Routes.MyPageEdit.route) {
                MyPageEditScreen(navController)
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

