package com.example.teamproject.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.teamproject.MenuItem
import com.example.teamproject.ViewModel.LibraryMenuViewModel
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel


@Composable
fun LibraryOrderScreen(
    menuItem: MenuItem,
    category: String,
    index: Int,
    libraryViewModel: LibraryMenuViewModel,
    onAddToCart: () -> Unit,
    onCheckout: () -> Unit
) {
    var quantity by remember { mutableStateOf(1) }
    var selectedSize by remember { mutableStateOf("기본") }
    var selectedSide by remember { mutableStateOf("None") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = menuItem.imageRes),
            contentDescription = menuItem.name,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = menuItem.name)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Price: ${menuItem.price}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Remaining Quantity: ${menuItem.quantity}")
        Spacer(modifier = Modifier.height(16.dp))

        // Quantity Selector
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Quantity:")
            Button(onClick = { if (quantity > 1) quantity-- }) {
                Text(text = "-")
            }
            Text(text = quantity.toString())
            Button(onClick = { if (quantity < menuItem.quantity) quantity++ }) {
                Text(text = "+")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Size Selector
        Text(text = "사이즈")
        Spacer(modifier = Modifier.height(8.dp))
        SizeSelector(selectedSize = selectedSize, onSizeSelected = { selectedSize = it })
        Spacer(modifier = Modifier.height(16.dp))

        // Side Selector
        Text(text = "사이드 메뉴")
        Spacer(modifier = Modifier.height(8.dp))
        SideSelector(selectedSide = selectedSide, onSideSelected = { selectedSide = it })
        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onAddToCart) {
                Text(text = "장바구니")
            }
            Button(onClick = {
                libraryViewModel.decreaseQuantity(category, index, quantity)
                onCheckout()
            }) {
                Text(text = "결제하기")
            }
        }
    }
}

@Composable
fun SizeSelector(selectedSize: String, onSizeSelected: (String) -> Unit) {
    val sizes = listOf("기본", "레귤러 (+1,200)", "점보 (+2,200)")

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        sizes.forEach { size ->
            Box(
                modifier = Modifier
                    .clickable { onSizeSelected(size) }
                    .border(
                        width = 2.dp,
                        color = if (selectedSize == size) Color.Blue else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(text = size)
            }
        }
    }
}

@Composable
fun SideSelector(selectedSide: String, onSideSelected: (String) -> Unit) {
    val sides = listOf("None", "Side1", "Side2")

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        sides.forEach { side ->
            Box(
                modifier = Modifier
                    .clickable { onSideSelected(side) }
                    .border(
                        width = 2.dp,
                        color = if (selectedSide == side) Color.Blue else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(text = side)
            }
        }
    }
}


//==========================================
@Composable
fun StudentUnionOrderScreen(
    menuItem: MenuItem,
    category: String,
    index: Int,
    studentUnionViewModel: StudentUnionMenuViewModel,
    onAddToCart: () -> Unit,
    onCheckout: () -> Unit
) {
    var quantity by remember { mutableStateOf(1) }
    var selectedSize by remember { mutableStateOf("기본") }
    var selectedSide by remember { mutableStateOf("None") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = menuItem.imageRes),
            contentDescription = menuItem.name,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = menuItem.name)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Price: ${menuItem.price}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Remaining Quantity: ${menuItem.quantity}")
        Spacer(modifier = Modifier.height(16.dp))

        // Quantity Selector
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Quantity:")
            Button(onClick = { if (quantity > 1) quantity-- }) {
                Text(text = "-")
            }
            Text(text = quantity.toString())
            Button(onClick = { if (quantity < menuItem.quantity) quantity++ }) {
                Text(text = "+")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Size Selector
        Text(text = "사이즈")
        Spacer(modifier = Modifier.height(8.dp))
        SizeSelector(selectedSize = selectedSize, onSizeSelected = { selectedSize = it })
        Spacer(modifier = Modifier.height(16.dp))

        // Side Selector
        Text(text = "사이드 메뉴")
        Spacer(modifier = Modifier.height(8.dp))
        SideSelector(selectedSide = selectedSide, onSideSelected = { selectedSide = it })
        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onAddToCart) {
                Text(text = "장바구니")
            }
            Button(onClick = {
                studentUnionViewModel.decreaseQuantity(category, index, quantity)
                onCheckout()
            }) {
                Text(text = "결제하기")
            }
        }
    }
}


