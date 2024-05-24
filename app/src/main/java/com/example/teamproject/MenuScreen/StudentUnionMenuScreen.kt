package com.example.teamproject.MenuScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.teamproject.Component.MenuGrid
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel


@Composable
fun StudentUnionBabScreen(viewModel: StudentUnionMenuViewModel = viewModel()) {
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
fun StudentUnionPopoScreen(viewModel: StudentUnionMenuViewModel = viewModel()) {
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
fun StudentUnionDonggasScreen(viewModel: StudentUnionMenuViewModel = viewModel()) {
    Column {
        MenuGrid(
            menuItems = viewModel.DonggasItems,
            onItemClick = { index ->
                viewModel.decreaseDonggasQuantity(index)
            }
        )
    }
}

@Composable
fun StudentUnionGookbabScreen(viewModel: StudentUnionMenuViewModel = viewModel()) {
    Column {
        MenuGrid(
            menuItems = viewModel.GookbabItems,
            onItemClick = { index ->
                viewModel.decreaseGookbabQuantity(index)
            }
        )
    }
}


@Composable
fun StudentUnionBoonsikScreen(viewModel: StudentUnionMenuViewModel = viewModel()) {
    Column {
        MenuGrid(
            menuItems = viewModel.BoonsikItems,
            onItemClick = { index ->
                viewModel.decreaseBoonsikQuantity(index)
            }
        )
    }
}

@Composable
fun StudentUnionMaraScreen(viewModel: StudentUnionMenuViewModel = viewModel()) {
    Column {
        MenuGrid(
            menuItems = viewModel.MaraItems,
            onItemClick = { index ->
                viewModel.decreaseMaraQuantity(index)
            }
        )
    }
}