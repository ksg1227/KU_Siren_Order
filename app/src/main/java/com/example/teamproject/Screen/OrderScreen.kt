package com.example.teamproject.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.teamproject.Item.CartMenuItem
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R
import com.example.teamproject.ViewModel.CartMenuViewModel
import com.example.teamproject.ViewModel.LibraryMenuViewModel
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel
import com.example.teamproject.navigation.Routes

@Composable
fun Library_GusiaOrderScreen( //사이드 있는 경우
    menuItem: MenuItem,
    category: String,
    index: Int,
    libraryViewModel: LibraryMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    cartViewModel: CartMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    onCheckout: () -> Unit,
    navController: NavHostController
) {
    val quantity by remember { mutableStateOf(1) }
    var selectedSize by remember { mutableStateOf("기본") }
    var selectedSides by remember { mutableStateOf(listOf<String>()) }

    val sizesAdditionalPrice = when (selectedSize) {
        "레귤러 (+1,200)" -> 1200
        "점보 (+2,200)" -> 2200
        "라지 (+1,000)" -> 1000
        else -> 0
    }

    val sideAdditionalPrice = selectedSides.map { side ->
        when (side) {
            "삼겹고기추가 (+1,000)" -> 1000
            "계란후라이 (+800)" -> 800
            "체다치즈 (+800)" -> 800
            "새우네트 (+2,500)" -> 2500
            "고구마롤 (+1,800)" -> 1800
            else -> 0
        }
    }.sum()

    val totalPrice = remember(quantity, selectedSize, selectedSides, menuItem.price) {
        (menuItem.price.toInt() + sizesAdditionalPrice + sideAdditionalPrice) * quantity
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            onBackIconClick = { navController.popBackStack() },
            title = "주문 화면",
            titleColor = Color.Black,
            onRightIconClick = { /*TODO*/ },
            rightIconImgId = null
        )
        Image(
            painter = painterResource(id = menuItem.imageRes),
            contentDescription = menuItem.name,
            modifier = Modifier
                .height(200.dp)
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

        val sizes = when (category) {
            "Bab" -> listOf("기본", "레귤러 (+1,200)", "점보 (+2,200)")
            "Popo" -> listOf("기본", "라지 (+1,000)")
            else -> listOf("기본") // 기본 리스트 설정
        }

        SizeSelector(
            selectedSize = selectedSize,
            onSizeSelected = { selectedSize = it },
            sizes = sizes
        )

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

        val sides = when (category) {
            "Bab" -> listOf("추가X", "삼겹고기추가 (+1,000)", "계란후라이 (+800)", "체다치즈 (+800)")
            "Popo" -> listOf("추가X", "새우네트 (+2,500)", "고구마롤 (+1,800)")
            else -> listOf("추가X") // 기본 리스트 설정
        }

        SideSelector(
            selectedSides = selectedSides,
            onSideSelected = { newSelectedSides -> selectedSides = newSelectedSides },
            sides = sides
        )

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.weight(1f))

        // Quantity Selector and Total Price
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${totalPrice}원",
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.LibraryGusia.route)

                    libraryViewModel.decreaseQuantity(category, index, quantity)

                    val cartMenuItem = CartMenuItem(menuItem.copy(quantity = quantity,
                        price = totalPrice.toString()),
                        size = selectedSize,
                        optionList = selectedSides)

                    cartViewModel.library_GusiaMenuList.add(cartMenuItem)
//                    cartViewModel.library_GusiaMenuList.add(
//                        menuItem.copy(
//                            quantity = quantity,
//                            price = totalPrice.toString()
//                        )
//                    )
                },   //==================================================================
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
fun Library_GusiaNoSideOrderScreen(      //side나 size 없는 경우
    menuItem: MenuItem,
    category: String,
    index: Int,
    libraryViewModel: LibraryMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    cartViewModel: CartMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    onCheckout: () -> Unit,
    navController: NavHostController
) {
    var quantity by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        TopAppBar(
            onBackIconClick = { navController.popBackStack() },
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

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Price
        Text(
            text = "가격 : ${menuItem.price}원",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Remaining Quantity
        Text(
            text = "잔여 수량 : ${menuItem.quantity}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                onClick = {
                    navController.navigate(Routes.LibraryGusia.route)

                    libraryViewModel.decreaseQuantity(category, index, quantity)

                    val cartMenuItem = CartMenuItem(menuItem.copy(quantity = quantity,
                        price = (menuItem.price.toInt() * quantity).toString()),
                        size = null,
                        optionList = null)

                    cartViewModel.library_GusiaMenuList.add(cartMenuItem)

//                    cartViewModel.library_GusiaMenuList.add(
//                        menuItem.copy(
//                            quantity = quantity,
//                            price = (menuItem.price.toInt() * quantity).toString()
//                        )
//                    )
                },    //==================================================================
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
                    libraryViewModel.decreaseQuantity(category, index, quantity)
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
fun StudentUnion_GusiaOrderScreen(   //사이드 메뉴 존재하는 경우
    menuItem: MenuItem,
    category: String,
    index: Int,
    studentUnionViewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    cartViewModel: CartMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    onCheckout: () -> Unit,
    navController: NavHostController
) {

    val quantity by remember { mutableStateOf(1) }
    var selectedSize by remember { mutableStateOf("기본") }
    var selectedSides by remember { mutableStateOf(listOf<String>()) }

    val sizeAdditionalPrice = when (selectedSize) {
        "레귤러 (+1,200)" -> 1200
        "점보 (+2,200)" -> 2200
        "라지 (+1,000)" -> 1000
        else -> 0
    }

    val sideAdditionalPrice = selectedSides.map { side ->
        when (side) {
            "삼겹고기추가 (+1,000)" -> 1000
            "계란후라이 (+800)" -> 800
            "체다치즈 (+800)" -> 800
            "새우네트 (+2,500)" -> 2500
            "고구마롤 (+1,800)" -> 1800
            "고기추가 (+1,000)" -> 1000
            "순대추가 (+1,000)" -> 1000
            "다대기 (+500)" -> 500
            "비엔나소시지 (+1,000)" -> 1000
            "백목이버섯 (+1,000)" -> 1000
            "옥수수면 (+1,000)" -> 1000
            "뉴진면 (+1,500)" -> 1500
            "소고기 (+1,500)" -> 1500
            "모듬야채 (+1,500)" -> 1500
            "모둠햄 (+2,000)" -> 2000
            "모둠버섯 (+2,000)" -> 2000
            "수제비 (+1,000)" -> 1000
            "고구마떡 (+1,000)" -> 1000
            "팽이버섯 (+1,000)" -> 1000
            else -> 0
        }
    }.sum()

    val totalPrice = remember(quantity, selectedSize, selectedSides, menuItem.price) {
        (menuItem.price.toInt() + sizeAdditionalPrice + sideAdditionalPrice) * quantity
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            onBackIconClick = { navController.popBackStack() },
            title = "주문 화면",
            titleColor = Color.Black,
            onRightIconClick = { /*TODO*/ },
            rightIconImgId = null
        )
        Image(
            painter = painterResource(id = menuItem.imageRes),
            contentDescription = menuItem.name,
            modifier = Modifier
                .height(200.dp)
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

        val sizes = when (category) {
            "Bab" -> listOf("기본", "레귤러 (+1,200)", "점보 (+2,200)")
            "Popo" -> listOf("기본", "라지 (+1,000)")
            else -> listOf("기본") // 기본 리스트 설정
        }

        SizeSelector(
            selectedSize = selectedSize,
            onSizeSelected = { selectedSize = it },
            sizes = sizes
        )

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

        val sides = when (category) {
            "Bab" -> listOf("추가X", "삼겹고기추가 (+1,000)", "계란후라이 (+800)", "체다치즈 (+800)")
            "Popo" -> listOf("추가X", "새우네트 (+2,500)", "고구마롤 (+1,800)")
            "Gookbab" -> listOf("추가X", "고기추가 (+1,000)", "순대추가 (+1,000)", "다대기 (+500)")
            "Mara" -> listOf(
                "추가X",
                "비엔나소시지 (+1,000)",
                "백목이버섯 (+1,000)",
                "옥수수면 (+1,000)",
                "뉴진면 (+1,500)",
                "소고기 (+1,500)",
                "모듬야채 (+1,500)",
                "모둠햄 (+2,000)",
                "모둠버섯 (+2,000)",
                "수제비 (+1,000)",
                "고구마떡 (+1,000)",
                "팽이버섯 (+1,000)"
            )

            else -> listOf("추가X") // 기본 리스트 설정
        }

        SideSelector(
            selectedSides = selectedSides,
            onSideSelected = { newSelectedSides -> selectedSides = newSelectedSides },
            sides = sides
        )

        Spacer(modifier = Modifier.padding(bottom = 20.dp))

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.weight(1f))

        // Quantity Selector and Total Price
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
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
                onClick = {
                    navController.navigate(Routes.StudentUnionGusia.route)

                    studentUnionViewModel.decreaseQuantity(category, index, quantity)

                    val cartMenuItem = CartMenuItem(menuItem.copy(quantity = quantity,
                        price = totalPrice.toString()),
                        size = selectedSize,
                        optionList = selectedSides)

                    cartViewModel.studentUnion_GusiaMenuList.add(cartMenuItem)

//                    cartViewModel.studentUnion_GusiaMenuList.add(
//                        menuItem.copy(
//                            quantity = quantity,
//                            price = totalPrice.toString()
//                        )
//                    )
                },    //==================================================================
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
fun StudentUnion_GusiaNoSideOrderScreen(    //side나 size 없는 경우
    menuItem: MenuItem,
    category: String,
    index: Int,
    studentUnionViewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    cartViewModel: CartMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    onCheckout: () -> Unit,
    navController: NavHostController
) {
    var quantity by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            onBackIconClick = { navController.popBackStack() },
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

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Price
        Text(
            text = "가격 : ${menuItem.price}원",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Remaining Quantity
        Text(
            text = "잔여 수량 : ${menuItem.quantity}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                onClick = {
                    navController.navigate(Routes.StudentUnionGusia.route)

                    studentUnionViewModel.decreaseQuantity(category, index, quantity)

                    val cartMenuItem = CartMenuItem(menuItem.copy(quantity = quantity,
                        price = (menuItem.price.toInt() * quantity).toString()),
                        size = null,
                        optionList = null)

                    cartViewModel.studentUnion_GusiaMenuList.add(cartMenuItem)
//                    cartViewModel.studentUnion_GusiaMenuList.add(
//                        menuItem.copy(
//                            quantity = quantity,
//                            price = (menuItem.price.toInt() * quantity).toString()
//                        )
//                    )
                },   //==================================================================
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
fun StudentUnion_FirstfloorOrderScreen(   //1층 학식 주문 화면
    menuItem: MenuItem,
    category: String,
    index: Int,
    studentUnionViewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    cartViewModel: CartMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    onCheckout: () -> Unit,
    navController: NavHostController
) {
    var quantity by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            onBackIconClick = { navController.popBackStack() },
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

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Price
        Text(
            text = "가격 : ${menuItem.price}원",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Remaining Quantity
        Text(
            text = "잔여 수량 : ${menuItem.quantity}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                text = "${menuItem.price.toInt() * quantity}원",
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 장바구니 담기 + 결제하기 부분
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.StudentUnionFirstfloor.route)

                    studentUnionViewModel.decreaseQuantity(category, index, quantity)

                    val cartMenuItem = CartMenuItem(menuItem.copy(quantity = quantity,
                        price = (menuItem.price.toInt() * quantity).toString()),
                        size = null,
                        optionList = null)

                    cartViewModel.studentUnion_FirstfloorMenuList.add(cartMenuItem)
//                    cartViewModel.studentUnion_FirstfloorMenuList.add(
//                        menuItem.copy(
//                            quantity = quantity,
//                            price = (menuItem.price.toInt() * quantity).toString()
//                        )
//                    )
                },  //==================================================================
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
fun SizeSelector(selectedSize: String, onSizeSelected: (String) -> Unit, sizes: List<String>) {

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
fun SideSelector(
    selectedSides: List<String>,
    onSideSelected: (List<String>) -> Unit,
    sides: List<String>
) {
    LazyHorizontalGrid(
        rows = GridCells.Adaptive(minSize = 60.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(180.dp)
    ) {
        items(sides) { side ->
            val isSelected = selectedSides.contains(side)
            Box(
                modifier = Modifier
                    .clickable {
                        val newSelectedSides = when (side) {
                            "추가X" -> listOf("추가X")
                            else -> {
                                if (selectedSides.contains("추가X")) {
                                    selectedSides - "추가X" + side
                                } else {
                                    if (isSelected) {
                                        selectedSides - side
                                    } else {
                                        selectedSides + side
                                    }
                                }
                            }
                        }
                        onSideSelected(newSelectedSides)
                    }
                    .background(
                        color = if (isSelected) Color.Gray else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
                    .size(100.dp)
            ) {
                Text(
                    text = side,
                    color = if (isSelected) Color.White else Color.Black
                )
            }
        }
    }
}






