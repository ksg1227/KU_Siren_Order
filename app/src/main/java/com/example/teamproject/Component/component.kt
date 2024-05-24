package com.example.teamproject.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teamproject.MenuItem
import com.example.teamproject.R

@Composable
fun MenuGrid(menuItems: List<MenuItem>, onItemClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 10.dp),
        contentPadding = PaddingValues(vertical = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(menuItems.size) { index ->
            val item = menuItems[index]
            KioskMenuItem(
                imageRes = item.imageRes,
                menuName = item.name,
                menuPrice = item.price,
                initialQuantity = item.quantity,
                onClick = { onItemClick(index) }
            )
        }
    }
}


@Composable
fun KioskMenuItem(
    imageRes: Int,
    menuName: String,
    menuPrice: String,
    initialQuantity: Int,
    onClick: () -> Unit
) {
    var quantity by remember { mutableStateOf(initialQuantity) }
    var displayImageRes by remember { mutableStateOf(imageRes) }
    var isClickable by remember { mutableStateOf(true) }

    if (quantity <= 0) {
        displayImageRes = R.drawable.soldout
        isClickable = false
    }

    // 메뉴 아이템이 클릭되지 않을 때 투명도를 조절하여 블러 처리
    val alpha = if (isClickable) 1f else 0.5f

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(9.dp),
        modifier = Modifier
            .clickable(enabled = isClickable) {
                if (isClickable) {
                    quantity--
                    if (quantity <= 0) {
                        displayImageRes = R.drawable.soldout
                        isClickable = false
                    }
                    onClick()
                }
            }
    ) {
        Box(modifier = Modifier.graphicsLayer(alpha = alpha)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = displayImageRes),
                    contentDescription = menuName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = menuName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = menuPrice,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 13.sp
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "잔여 수량: $quantity",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}





