package com.example.teamproject.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teamproject.MenuItem

@Composable
fun MenuGrid(menuItems: List<MenuItem>) {
    // Remember the menu items to avoid unnecessary recompositions
    val items = remember { menuItems }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 10.dp),
        contentPadding = PaddingValues(vertical = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(items.size) { index ->
            val item = items[index]
            KioskMenuItem(
                imageRes = item.imageRes,
                menuName = item.name,
                onClick = { /* TODO: Handle click action */ }
            )
        }
    }
}

//@Composable
//fun MenuGrid(menuItems: List<MenuItem>) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//        modifier = Modifier
//            .fillMaxHeight() // 전체 높이를 채우도록 설정
//            .padding(horizontal = 10.dp),
//        contentPadding = PaddingValues(vertical = 30.dp), // 상단 패딩을 최소화
//        horizontalArrangement = Arrangement.spacedBy(10.dp),
//        verticalArrangement = Arrangement.spacedBy(15.dp)
//    ) {
//        items(menuItems.size) { index ->
//            val item = menuItems[index]
//            KioskMenuItem(
//                imageRes = item.imageRes,
//                menuName = item.name,
//                onClick = { /* TODO: Handle click action */ }
//            )
//        }
//    }
//}

@Composable
fun KioskMenuItem(
    imageRes: Int,
    menuName: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(9.dp),
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
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
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

