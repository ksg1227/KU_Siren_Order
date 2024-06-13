package com.example.teamproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.MyPage.MyPageShowScreen
import com.example.teamproject.R
import com.example.teamproject.Restaurant.Library_GusiaScreen
import com.example.teamproject.Restaurant.StudentUnion_FirstfloorScreen
import com.example.teamproject.Restaurant.StudentUnion_GusiaScreen
import com.example.teamproject.Restio.AnimalLifeRestioScreen
import com.example.teamproject.Restio.EngineeringRestioScreen
import com.example.teamproject.Restio.IndustryRestioScreen
import com.example.teamproject.Restio.LibraryRestioScreen
import com.example.teamproject.Restio.ManageMentRestioScreen
import com.example.teamproject.Restio.RestioPayScreen
import com.example.teamproject.Screen.CafeteriaRestioSelScreen
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
import com.example.teamproject.ViewModel.UserViewModel
import com.example.teamproject.ViewModel.rememberViewModelStoreOwner
import com.example.teamproject.mypage.MyPageEditScreen
import com.example.teamproject.mypage.MyPageMainScreen

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
    object AnimalLifeRestioScreen : Routes("animallife_restio_screen")
    object EngineeringRestioScreen : Routes("engineering_restio_screen")
    object IndustryRestioScreen : Routes("industry_restio_screen")
    object LibraryRestioScreen : Routes("library_restio_screen")
    object ManagementRestioScreen : Routes("management_restio_screen")
    object MyPageMainScreen : Routes("mypage_main_screen")
    object MyPageEditScreen : Routes("mypage_edit_screen")
    object MyPageShowScreen : Routes("mypage_show_screen")
    object CafeteriaRestioSelScreen : Routes("cafeteria_restio_sel_screen")

}


@Composable
fun NavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel(),
    libraryViewModel : LibraryMenuViewModel = viewModel(),
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
                LoginScreen(navController,userViewModel)
            }
            composable(Routes.SignUp.route) {
                SignUpScreen(navController,userViewModel)
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

            // Restio
            composable(Routes.AnimalLifeRestioScreen.route) {
                AnimalLifeRestioScreen(title = "동물생명과학관 레스티오",navController)
            }

            composable(Routes.EngineeringRestioScreen.route) {
                EngineeringRestioScreen(title = "공학관 레스티오", navController)
            }

            composable(Routes.IndustryRestioScreen.route) {
                IndustryRestioScreen(title = "산학협동관 레스티오", navController)
            }

            composable(Routes.LibraryRestioScreen.route) {
                LibraryRestioScreen(title = "상허기념도서관 레스티오", navController)
            }

            composable(Routes.ManagementRestioScreen.route) {
                ManageMentRestioScreen(title = "경영관 레스티오", navController)
            }

            // MyPage 3
            composable(Routes.MyPageMainScreen.route) {
                MyPageMainScreen(navController)
            }

            composable(Routes.MyPageEditScreen.route) {
                MyPageEditScreen(navController)
            }

            composable(Routes.MyPageShowScreen.route) {
                MyPageShowScreen(navController)
            }
            composable("cart_screen/{placeName}") {backStackEntry ->
                val placeName = backStackEntry.arguments?.getString("placeName") ?: ""
                CartScreen(navController, placeName)
            }

            // CafeteriaRestioSel
            composable(Routes.CafeteriaRestioSelScreen.route) {
                CafeteriaRestioSelScreen(navController)
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

            composable("restioPay/{place}/{productName}/{productPrice}/{productImageRes}/{productCategory}") { backStackEntry ->
                val place = backStackEntry.arguments?.getString("place") ?: ""
                val product = MenuItem(
                    imageRes = backStackEntry.arguments?.getString("productImageRes")?.toInt() ?: R.drawable.konkuk,
                    name = backStackEntry.arguments?.getString("productName") ?: "",
                    price = backStackEntry.arguments?.getString("productPrice") ?: "",
                    quantity = 1, // Initial quantity set to 1
                    category = backStackEntry.arguments?.getString("productCategory") ?: "",
                    index = 0 // Provide the appropriate index
                )
                RestioPayScreen(
                    place = place,
                    product = product,
                    onClose = { navController.popBackStack() }
                )
            }
        }
    }
}

