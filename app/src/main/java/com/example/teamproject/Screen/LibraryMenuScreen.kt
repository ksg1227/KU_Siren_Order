package com.example.teamproject.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.teamproject.Component.MenuGrid
import com.example.teamproject.ViewModel.LibraryMenuViewModel
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner

@Composable
fun LibraryBabScreen(viewModel: LibraryMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.BabItems,
            onItemClick = { index ->
                val item = viewModel.BabItems[index]
                navController.navigate("library_order_screen/Bab/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}

@Composable
fun LibraryPopoScreen(viewModel: LibraryMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.PopoItems,
            onItemClick = { index ->
                val item = viewModel.PopoItems[index]
                navController.navigate("library_order_screen/Popo/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}

@Composable
fun LibraryDonggasScreen(viewModel: LibraryMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current), navController: NavHostController) {
    Column {
        MenuGrid(
            menuItems = viewModel.DonggasItems,
            onItemClick = { index ->
                val item = viewModel.DonggasItems[index]
                navController.navigate("library_order_screen/Donggas/$index/${item.imageRes}/${item.name}/${item.price}/${item.quantity}")
            }
        )
    }
}