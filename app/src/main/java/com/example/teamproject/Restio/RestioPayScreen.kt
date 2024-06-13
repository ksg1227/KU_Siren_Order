package com.example.teamproject.Restio

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.teamproject.Item.CartMenuItem
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R
import com.example.teamproject.Screen.TopAppBar
import com.example.teamproject.ViewModel.CartMenuViewModel
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.navigation.Routes

@Composable
fun RestioPayScreen(
    place: String,
    product: MenuItem,
    onClose: () -> Unit,
    viewModel: CartMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    navController: NavHostController
) {

    val options1 = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "샷추가" to 500,
        "휘핑크림추가" to 500,
        "바닐라시럽추가" to 300,
        "헤이즐넛시럽추가" to 300,
        "텀블러할인" to -200
    )

    val options2 = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "샷추가" to 500,
        "휘핑크림추가" to 500,
        "바닐라시럽추가" to 300,
        "헤이즐넛시럽추가" to 300,
        "텀블러할인" to -200
    )

    val options3 = listOf(
        "샷추가" to 500,
        "텀블러할인" to -200
    )

    val options4 = listOf(
        "텀블러할인" to -200
    )

    val options5 = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "텀블러할인" to -200
    )

    val options6 = listOf(
        "" to 0
    )

    val options7 = listOf(
        "" to 0
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
    var quantity by remember { mutableStateOf(1) }

    val productPrice = product.price.replace("원", "").replace(",", "").toInt()

    val totalOptionPrice =
        selectedOptions.sumOf { it.second * optionCounts[selectedOptions.indexOf(it)] }
    val totalPrice = (productPrice + totalOptionPrice) * quantity

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopAppBar(
                onBackIconClick = { onClose() },
                title = "주문화면",
                titleColor = Color.Black,
                onRightIconClick = { },
                rightIconImgId = null
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.konkuk), // 이미지 받아오기
                        contentDescription = null
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${product.name}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))


                var selectedOptionsState by remember { mutableStateOf(List(selectedOptions.size) { false }) }

                selectedOptions.forEachIndexed { index, (option, price) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (selectedOptionsState[index]) Color.Gray else Color.Transparent) // 색상 적용 추가하기
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                            .clickable {
                                selectedOptionsState = selectedOptionsState
                                    .toMutableList()
                                    .apply {
                                        set(index, !selectedOptionsState[index])
                                    }
                                optionCounts = optionCounts
                                    .toMutableList()
                                    .apply {
                                        set(index, if (selectedOptionsState[index]) 1 else 0)
                                    }
                            }
                            .padding(8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "$option", fontSize = 16.sp)
                            Text(text = "(₩$price)", fontSize = 16.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                Text(text = "수량", fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            onClick = { if (quantity > 1) quantity-- },
                        ) {
                            Text(text = "-")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "$quantity", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { quantity++ },
                        ) {
                            Text(text = "+")
                        }
                    }
                    Text(
                        text = "총 가격: ₩ $totalPrice",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(onClick = {
                        val selectedOptionList = mutableListOf<String>()
                        selectedOptions.forEachIndexed { index, (option, price) ->
                            if (optionCounts[index] > 0) {
                                selectedOptionList.add("$option (${price}): ${optionCounts[index]}")
                            }
                        }
                        val cartItem = CartMenuItem(
                            menuItem = product.copy(
                                quantity = quantity,
                                price = totalPrice.toString()
                            ),
                            size = null,
                            optionList = selectedOptionList
                        )
                        when (place) {
                            "공학관 레스티오" -> {
                                viewModel.engineeringRestioMenuList.add(cartItem)
                            }

                            "동물생명과학관 레스티오" -> {
                                viewModel.animalLifeRestioMenuList.add(cartItem)
                            }

                            "상허기념도서관 레스티오" -> {
                                viewModel.libraryRestioMenuList.add(cartItem)
                            }

                            "산학협동관 레스티오" -> {
                                viewModel.industryRestioMenuList.add(cartItem)
                            }

                            "경영관 레스티오" -> {
                                viewModel.managementRestioMenuList.add(cartItem)
                            }
                        }

                        onClose()
                    }, modifier = Modifier.weight(1f)) {
                        Text(text = "담기")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { navController.navigate(Routes.Payment.route) }, modifier = Modifier.weight(1f)) {
                        Text(text = "결제하기")
                    }
                }
            }
        }
    }
}

//selectedOptions.forEachIndexed { index, (option, price) ->
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(text = "+$option", fontSize = 16.sp)
//                Text(text = "₩ $price", fontSize = 16.sp)
//            }
//            Spacer(modifier = Modifier.height(4.dp))
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Button(
//                    onClick = {
//                        if (optionCounts[index] > 0) optionCounts =
//                            optionCounts.toMutableList()
//                                .apply { set(index, get(index) - 1) }
//                    },
//                ) {
//                    Text(text = "-")
//                }
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(text = "${optionCounts[index]}", fontSize = 16.sp)
//                Spacer(modifier = Modifier.width(8.dp))
//                Button(
//                    onClick = {
//                        optionCounts = optionCounts.toMutableList()
//                            .apply { set(index, get(index) + 1) }
//                    },
//                ) {
//                    Text(text = "+")
//                }
//            }
//        }
//
//    }
//}

//Row(
//verticalAlignment = Alignment.CenterVertically,
//modifier = Modifier.fillMaxWidth()
//) {
//    Checkbox(
//        checked = checkedStates[index],
//        onCheckedChange = {
//            checkedStates = checkedStates.toMutableList().apply {
//                set(index, it)
//            }
//            optionCounts = optionCounts.toMutableList().apply {
//                set(index, if (it) 1 else 0)
//            }
//        }
//    )
//    Spacer(modifier = Modifier.width(8.dp))
//    Text(text = "${optionCounts[index]}", fontSize = 16.sp)
//}

