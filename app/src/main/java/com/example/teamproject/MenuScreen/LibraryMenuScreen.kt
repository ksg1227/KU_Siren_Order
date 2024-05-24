package com.example.teamproject.MenuScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teamproject.Component.MenuGrid
import com.example.teamproject.ViewModel.LibraryMenuViewModel

@Composable
fun LibraryBabScreen(viewModel: LibraryMenuViewModel = viewModel()) {
    Column {
        MenuGrid(
            menuItems = viewModel.BabItems,
            onItemClick = { index ->
                viewModel.decreaseBabQuantity(index)
            }
        )
    }
}

@Composable
fun LibraryPopoScreen(viewModel: LibraryMenuViewModel = viewModel()) {
    Column {
        MenuGrid(
            menuItems = viewModel.PopoItems,
            onItemClick = { index ->
                viewModel.decreasePopoQuantity(index)
            }
        )
    }
}

@Composable
fun LibraryDonggasScreen(viewModel: LibraryMenuViewModel = viewModel()) {
    Column {
        MenuGrid(
            menuItems = viewModel.DonggasItems,
            onItemClick = { index ->
                viewModel.decreaseDonggasQuantity(index)
            }
        )
    }
}