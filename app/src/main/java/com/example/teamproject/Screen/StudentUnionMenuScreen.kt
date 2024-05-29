package com.example.teamproject.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.teamproject.Component.MenuGrid
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel


@Composable
fun StudentUnionBabScreen(viewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.BabItems,
            onItemClick = { index ->
                val item = viewModel.BabItems[index]
                navController.navigate("studentUnion_order_screen/Bab/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}

@Composable
fun StudentUnionPopoScreen(viewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.PopoItems,
            onItemClick = { index ->
                val item = viewModel.PopoItems[index]
                navController.navigate("studentUnion_order_screen/Popo/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}

@Composable
fun StudentUnionDonggasScreen(viewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.DonggasItems,
            onItemClick = { index ->
                val item = viewModel.DonggasItems[index]
                navController.navigate("studentUnion_order_screen/Donggas/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}

@Composable
fun StudentUnionGookbabScreen(viewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.GookbabItems,
            onItemClick = { index ->
                val item = viewModel.GookbabItems[index]
                navController.navigate("studentUnion_order_screen/Gookbab/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}


@Composable
fun StudentUnionBoonsikScreen(viewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.BoonsikItems,
            onItemClick = { index ->
                val item = viewModel.BoonsikItems[index]
                navController.navigate("studentUnion_order_screen/Boonsik/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}

@Composable
fun StudentUnionMaraScreen(viewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.MaraItems,
            onItemClick = { index ->
                val item = viewModel.MaraItems[index]
                navController.navigate("studentUnion_order_screen/Mara/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}