package com.example.teamproject.Restio

//import androidx.compose.material.icons.filled.Remove
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RestioPayScreen(product: Product, onClose: () -> Unit) {
    val options1 = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "샷추가" to 500,
        "휘핑크림추가" to 500,
        "바닐라시럽추가" to 300,
        "헤이즐넛시럽추가" to 300,
        "텀블러할인" to 200
    )

    val options2 = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "샷추가" to 500,
        "휘핑크림추가" to 500,
        "바닐라시럽추가" to 300,
        "헤이즐넛시럽추가" to 300,
        "텀블러할인" to 200
    )

    val options3 = listOf(
        "샷추가" to 500,
        "텀블러할인" to 200
    )

    val options4 = listOf(
        "텀블러할인" to 200
    )

    val options5 = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "텀블러할인" to 200
    )

    val options6 = listOf(
        "브라우니쿠키" to 3200,
        "체커쿠키" to 3200,
        "초코칩쿠키" to 3200,
        "초코칩머핀" to 2200,
        "치즈머핀" to 2200,
        "블루베리머핀" to 2200,
        "플레인와플" to 2200
    )

    val options7 = listOf(
        "레스티오치아바타" to 3100,
        "카야샌드위치" to 2200,
        "햄치즈샌드위치" to 2600,
        "오리지날핫도그" to 3200,
        "칠리핫도그" to 3200
    )

    val selectedOptions = when (product.category) {
        "커피 HOT" -> options1
        "커피 ICE" -> options2
        "티라떼/아이스티" -> options3
        "에이드/티백" -> options4
        "레스치노/스무디/과일주스" -> options5
        "베이커리" -> options6
        "샌드위치/핫도그" -> options7
        else -> options1
    }

    var optionCounts by remember { mutableStateOf(List(selectedOptions.size) { 0 }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(text = "(L)${product.name}", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        selectedOptions.forEachIndexed { index, (option, price) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "+$option", fontSize = 16.sp)
                        Text(text = "₩ $price", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            onClick = { if (optionCounts[index] > 0) optionCounts = optionCounts.toMutableList().apply { set(index, get(index) - 1) } },
                        ) {
                            Text(text = "-")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "${optionCounts[index]}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { optionCounts = optionCounts.toMutableList().apply { set(index, get(index) + 1) } },
                        ) {
                            Text(text = "+")
                        }
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onClose) {
                Text(text = "취소")
            }
            Button(onClick = { /* 선택 완료 처리 */ }) {
                Text(text = "선택완료")
            }
        }
    }
}
