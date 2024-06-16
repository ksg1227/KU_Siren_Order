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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.teamproject.Component.BtnRectangle
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

    val options1: List<Pair<String, Int>> = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "샷추가" to 500,
        "휘핑크림추가" to 500,
        "바닐라시럽추가" to 300,
        "헤이즐넛시럽추가" to 300,
        "텀블러할인" to -200
    )

    val options2: List<Pair<String, Int>> = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "샷추가" to 500,
        "휘핑크림추가" to 500,
        "바닐라시럽추가" to 300,
        "헤이즐넛시럽추가" to 300,
        "텀블러할인" to -200
    )

    val options3: List<Pair<String, Int>> = listOf(
        "샷추가" to 500,
        "텀블러할인" to -200
    )

    val options4: List<Pair<String, Int>> = listOf(
        "텀블러할인" to -200
    )

    val options5: List<Pair<String, Int>> = listOf(
        "휘핑크림 O" to 0,
        "휘핑크림 X" to 0,
        "텀블러할인" to -200
    )

    val options6: List<Pair<String, Int>>? = null // 옵션이 없는 경우 null 처리

    val selectedOptions: List<Pair<String, Int>>? = when (product.category) {
        "커피 HOT" -> options1
        "커피 ICE" -> options2
        "티라떼/아이스티" -> options3
        "에이드/티백" -> options4
        "레스치노/스무디/과일주스" -> options5
        "베이커리" -> options6
        "쿠키|머핀|와플" -> options6
        "샌드위치/핫도그" -> options6
        "베이글|크림치즈" -> options6
        else -> options6
    }

    var optionCounts by remember { mutableStateOf(List(selectedOptions?.size ?: 0) { 0 }) }
    var quantity by remember { mutableStateOf(1) }

    val productPrice = product.price.replace("원", "").replace(",", "").toInt()

    val totalOptionPrice = selectedOptions?.mapIndexed { index, (_, price) ->
        price * optionCounts[index]
    }?.sum() ?: 0
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
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(product.imageRes), // 이미지 받아오기
                        contentDescription = null
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${product.name}",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (selectedOptions != null) {
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
                                Text(text = "$option",
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_medium))
                                )
                                if(price > 0)
                                    Text(text = "(+$price)",
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.pretendard_medium))
                                    )
                                else
                                    Text(text = "($price)",
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.pretendard_medium))
                                    )
                            }
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
                        Image(
                            painter = painterResource(id = R.drawable.ic_minus), // 이미지 리소스
                            contentDescription = "Decrease Quantity",
                            modifier = Modifier
                                .clickable { if (quantity > 1) quantity-- }
                                .size(36.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "$quantity", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_plus), // 이미지 리소스
                            contentDescription = "Increase Quantity",
                            modifier = Modifier
                                .clickable { quantity++ }
                                .size(36.dp)
                        )
                    }
                    Text(
                        text = "총 가격: ₩ $totalPrice",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    )

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        BtnRectangle(
                            text = "담기",
                            textColorId = R.color.black,
                            bgColorId = R.color.gray_e6e6e6,
                            width = null,
                        ) {
                            val selectedOptionList = mutableListOf<String>()
                            selectedOptions?.forEachIndexed { index, (option, price) ->
                                if (optionCounts[index] > 0) {
                                    if(price > 0)
                                        selectedOptionList.add("$option (+${price}): ${optionCounts[index]}")
                                    else
                                        selectedOptionList.add("$option (${price}): ${optionCounts[index]}")
                                }
                            }
                            val cartItem = CartMenuItem(
                                menuItem = product.copy(
                                    quantity = quantity,
                                    price = totalPrice.toString()
                                ),
                                size = null,
                                optionList = selectedOptionList.map { it.split(":")[0].trim() }
                            )
                            when (place) {
                                "레스티오 공학관" -> {
                                    viewModel.engineeringRestioMenuList.add(cartItem)
                                }

                                "레스티오 동물생명과학관" -> {
                                    viewModel.animalLifeRestioMenuList.add(cartItem)
                                }

                                "레스티오 상허기념도서관" -> {
                                    viewModel.libraryRestioMenuList.add(cartItem)
                                }

                                "레스티오 산학협동관" -> {
                                    viewModel.industryRestioMenuList.add(cartItem)
                                }

                                "경영관 레스티오" -> {
                                    viewModel.managementRestioMenuList.add(cartItem)
                                }
                            }

                            onClose()
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(modifier = Modifier.weight(1f)) {
                        BtnRectangle(
                            text = "결제하기",
                            textColorId = R.color.white,
                            bgColorId = R.color.green_65a25b,
                            width = null
                        ) {
                            navController.navigate("routes_payment_route/$place/$totalPrice")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
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

