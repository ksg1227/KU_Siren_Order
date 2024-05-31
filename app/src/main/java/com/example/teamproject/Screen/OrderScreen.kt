package com.example.teamproject.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R
import com.example.teamproject.ViewModel.LibraryMenuViewModel
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel
import com.example.teamproject.navigation.Routes

@Composable
fun LibraryOrderScreen(
    menuItem: MenuItem,
    category: String,
    index: Int,
    libraryViewModel: LibraryMenuViewModel,
    onAddToCart: () -> Unit,
    onCheckout: () -> Unit,
    navController: NavHostController
) {
    var quantity by remember { mutableStateOf(0) }
    var selectedSize by remember { mutableStateOf("기본") }
    var selectedSide by remember { mutableStateOf("None") }

    // 선택된 사이드들의 추가 금액 합산

    val additionalPrice = when (selectedSize) {
        "레귤러 (+1,200)" -> 1200
        "점보 (+2,200)" -> 2200
        else -> 0
    }

    val totalPrice = remember(quantity, selectedSize, menuItem.price) {
        (menuItem.price.toInt() + additionalPrice) * quantity
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            onBackIconClick = { navController.navigate(Routes.LibraryGusia.route) },
            title = "주문 화면",
            titleColor = Color.Black,
            onRightIconClick = { /*TODO*/ },
            rightIconImgId = null
        )
        Image(
            painter = painterResource(id = menuItem.imageRes),
            contentDescription = menuItem.name,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )

        // Menu Name
        Text(
            text = menuItem.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 20.sp
        )

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        // Size Selector
        Text(
            text = "사이즈",
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        SizeSelector(selectedSize = selectedSize, onSizeSelected = { selectedSize = it })

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        // Side Selector
        Text(
            text = "사이드 메뉴",
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        SideSelector(selectedSide = selectedSide, onSideSelected = { selectedSide = it })

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.weight(1f))

        // Quantity Selector and Total Price
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = "Decrease Quantity",
                    modifier = Modifier
                        .clickable { if (quantity > 0) quantity-- }
                        .size(36.dp)
                )
                Text(
                    text = quantity.toString(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Increase Quantity",
                    modifier = Modifier
                        .clickable {
                            if (quantity < menuItem.quantity) {
                                quantity++
                            }
                        }
                        .size(36.dp)
                )
            }
            Text(
                text = "${totalPrice}원",
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onAddToCart,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE6E6E6),
                    contentColor = Color.Black
                ),
                enabled = quantity > 0 // 수량이 0개 이상일 때만 활성화
            ) {
                Text(
                    text = "담기",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    libraryViewModel.decreaseQuantity(category, index, quantity)
                    onCheckout()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF65A25B),
                    contentColor = Color.White
                ),
                enabled = quantity > 0
            ) {
                Text(
                    text = "결제하기",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 16.sp
                )
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
                    .background(
                        color = if (selectedSize == size) Color.Gray else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (selectedSize == size) Color.Black else Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = size,
                    color = if (selectedSize == size) Color.White else Color.Black
                )
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
                    .background(
                        color = if (selectedSide == side) Color.Gray else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (selectedSide == side) Color.Black else Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = side,
                    color = if (selectedSide == side) Color.White else Color.Black
                )
            }
        }
    }
}


//==========================================
@Composable
fun StudentUnion_GusiaOrderScreen(
    menuItem: MenuItem,
    category: String,
    index: Int,
    studentUnionViewModel: StudentUnionMenuViewModel,
    onAddToCart: () -> Unit,
    onCheckout: () -> Unit,
    navController : NavHostController
) {
    var quantity by remember { mutableStateOf(0) }
    var selectedSize by remember { mutableStateOf("기본") }
    var selectedSide by remember { mutableStateOf("None") }

    val additionalPrice = when (selectedSize) {
        "레귤러 (+1,200)" -> 1200
        "점보 (+2,200)" -> 2200
        else -> 0
    }

    val totalPrice = remember(quantity, selectedSize, menuItem.price) {
        (menuItem.price.toInt() + additionalPrice) * quantity
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            onBackIconClick = { navController.navigate(Routes.StudentUnionGusia.route) },
            title = "주문 화면",
            titleColor = Color.Black,
            onRightIconClick = { /*TODO*/ },
            rightIconImgId = null
        )
        Image(
            painter = painterResource(id = menuItem.imageRes),
            contentDescription = menuItem.name,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )


        // Menu Name
        Text(
            text = menuItem.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 20.sp
        )

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        // Size Selector
        Text(
            text = "사이즈",
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        SizeSelector(selectedSize = selectedSize, onSizeSelected = { selectedSize = it })

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        // Side Selector
        Text(
            text = "사이드 메뉴",
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        SideSelector(selectedSide = selectedSide, onSideSelected = { selectedSide = it })

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.weight(1f))

        // Quantity Selector and Total Price
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_minus), // 이미지 리소스
                    contentDescription = "Decrease Quantity",
                    modifier = Modifier
                        .clickable { if (quantity > 0) quantity-- }
                        .size(36.dp)
                )
                Text(
                    text = quantity.toString(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_plus), // 이미지 리소스
                    contentDescription = "Increase Quantity",
                    modifier = Modifier
                        .clickable { if (quantity < menuItem.quantity) quantity++ }
                        .size(36.dp)
                )
            }
            Text(
                text = "${totalPrice}원",
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onAddToCart,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE6E6E6),
                    contentColor = Color.Black
                ),
                enabled = quantity > 0
            ) {
                Text(
                    text = "담기",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    studentUnionViewModel.decreaseQuantity(category, index, quantity)
                    onCheckout()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF65A25B), // 배경색
                    contentColor = Color.White // 텍스트 색상
                ),
                enabled = quantity > 0
            ) {
                Text(
                    text = "결제하기",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Composable
fun StudentUnion_FirstfloorOrderScreen(
    menuItem: MenuItem,
    category: String,
    index: Int,
    studentUnionViewModel: StudentUnionMenuViewModel,
    onAddToCart: () -> Unit,
    onCheckout: () -> Unit,
    navController : NavHostController
) {
    var quantity by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            onBackIconClick = { navController.navigate(Routes.StudentUnionFirstfloor.route) },
            title = "주문 화면",
            titleColor = Color.Black,
            onRightIconClick = { /*TODO*/ },
            rightIconImgId = null
        )
        Image(
            painter = painterResource(id = menuItem.imageRes),
            contentDescription = menuItem.name,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Menu Name
        Text(
            text = menuItem.name,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 20.sp
        )

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Price
        Text(
            text = "가격 : ${menuItem.price}원",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Remaining Quantity
        Text(
            text = "잔여 수량 : ${menuItem.quantity}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.weight(1f))

        // Quantity Selector and Total Price
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_minus), // 이미지 리소스
                    contentDescription = "Decrease Quantity",
                    modifier = Modifier
                        .clickable { if (quantity > 1) quantity-- }
                        .size(36.dp)
                )
                Text(
                    text = quantity.toString(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_plus), // 이미지 리소스
                    contentDescription = "Increase Quantity",
                    modifier = Modifier
                        .clickable { if (quantity < menuItem.quantity) quantity++ }
                        .size(36.dp)
                )
            }
            Text(
                text = "${menuItem.price.toInt() * quantity}원",
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onAddToCart,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE6E6E6),
                    contentColor = Color.Black
                ),
                enabled = quantity > 0
            ) {
                Text(
                    text = "담기",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    studentUnionViewModel.decreaseQuantity(category, index, quantity)
                    onCheckout()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF65A25B), // 배경색
                    contentColor = Color.White // 텍스트 색상
                ),
                enabled = quantity > 0
            ) {
                Text(
                    text = "결제하기",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 16.sp
                )
            }
        }
    }
}




