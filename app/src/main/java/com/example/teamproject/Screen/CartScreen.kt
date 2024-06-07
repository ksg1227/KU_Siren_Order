package com.example.teamproject.Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R
import com.example.teamproject.ViewModel.CartMenuViewModel
import com.example.teamproject.ViewModel.LibraryMenuViewModel
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.ViewModel.StudentUnionMenuViewModel
import com.example.teamproject.navigation.Routes

@Composable
fun CartScreen(
    navController: NavHostController,
    placeName: String,
    cartMenuViewModel: CartMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    libraryMenuViewModel: LibraryMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current),
    studentUnionMenuViewModel: StudentUnionMenuViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    val resName: String;
    val resNameColor: Int;

    resName = placeName    //매장 이름
    resNameColor = R.color.green_066b3f

    var totalPrice = cartMenuViewModel.calculateTotalPrice(placeName)

    Column(
        modifier = Modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TopAppBar( // 상단바
            onBackIconClick = {
                navController.popBackStack()
            },
            title = "장바구니",
            titleColor = Color.Black,
            onRightIconClick = { },
            rightIconImgId = null
        )

        SelectAndDeleteBox(
            placeName,
            cartMenuViewModel,
            libraryMenuViewModel,
            studentUnionMenuViewModel
        ) // '전체선택' 체크박스 행

        Divider(
            color = colorResource(id = R.color.gray_b3b3b3),
            modifier = Modifier.height(1.dp)
        )

        Text( // 가게 이름
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            text = resName,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            color = colorResource(id = resNameColor)
        )

        Divider(
            color = colorResource(id = R.color.gray_b3b3b3),
            modifier = Modifier.height(1.dp)
        )

        // 장바구니 제품 목록
        CartItemUI(cartMenuViewModel, libraryMenuViewModel, studentUnionMenuViewModel, placeName)

        Spacer(Modifier.weight(1f))

        GoOrderBtn(onClick = {
//            Toast.makeText(context, "주문하기 버튼 클릭됨!", Toast.LENGTH_SHORT).show()

            when (placeName) {
                "학생회관 1층 학식" -> {
                    for (menu in cartMenuViewModel.studentUnion_FirstfloorMenuList) {
                        studentUnionMenuViewModel.decreaseQuantity(
                            menu.menuItem.category,
                            menu.menuItem.index,
                            menu.menuItem.quantity
                        )
                    }

                    cartMenuViewModel.studentUnion_FirstfloorMenuList.clear()
                }

                "학생회관 지하 학식(구시아푸드)" -> {
                    for (menu in cartMenuViewModel.studentUnion_GusiaMenuList) {
                        studentUnionMenuViewModel.decreaseQuantity(
                            menu.menuItem.category,
                            menu.menuItem.index,
                            menu.menuItem.quantity
                        )
                    }

                    cartMenuViewModel.studentUnion_GusiaMenuList.clear()
                }

                "상허기념도서관 지하 학식(구시아푸드)" -> {
                    for (menu in cartMenuViewModel.library_GusiaMenuList) {
                        libraryMenuViewModel.decreaseQuantity(
                            menu.menuItem.category,
                            menu.menuItem.index,
                            menu.menuItem.quantity
                        )
                    }

                    cartMenuViewModel.library_GusiaMenuList.clear()
                }
            }

            if(totalPrice==0){
                Toast.makeText(context, "장바구니에 메뉴를 먼저 담아주세요!", Toast.LENGTH_SHORT).show()
            }else{
                navController.navigate(Routes.Payment.route) // 결제화면으로 이동
            }

        }, totalPrice = totalPrice) // '주문하기' btn
        Spacer(modifier = Modifier.height(30.dp))
    }
}

// '전체선택' 체크박스 행
@Composable
fun SelectAndDeleteBox(
    placeName: String,
    cartMenuViewModel: CartMenuViewModel,
    libraryMenuViewModel: LibraryMenuViewModel,
    studentUnionMenuViewModel: StudentUnionMenuViewModel
) {
    var checked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            modifier = Modifier
                .padding(10.dp, 0.dp, 0.dp, 0.dp),
            onCheckedChange = { isChecked ->
                checked = isChecked
                when (placeName) {
                    "학생회관 1층 학식" -> {
                        if (checked) {
                            cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.addAll(
                                cartMenuViewModel.studentUnion_FirstfloorMenuList
                            )
                        } else {
                            cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.clear()
                        }
                    }

                    "학생회관 지하 학식(구시아푸드)" -> {
                        if (checked) {
                            cartMenuViewModel.selected_studentUnion_GusiaMenuList.addAll(
                                cartMenuViewModel.studentUnion_GusiaMenuList
                            )
                        } else {
                            cartMenuViewModel.selected_studentUnion_GusiaMenuList.clear()
                        }
                    }

                    "상허기념도서관 지하 학식(구시아푸드)" -> {
                        if (checked) {
                            cartMenuViewModel.selected_library_GusiaMenuList.addAll(
                                cartMenuViewModel.library_GusiaMenuList
                            )
                        } else {
                            cartMenuViewModel.selected_library_GusiaMenuList.clear()
                        }
                    }
                }
            }
        )
        Text(
            text = "전체선택",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier.clickable {
                checked = !checked
                when (placeName) {
                    "학생회관 1층 학식" -> {
                        if (checked) {
                            cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.addAll(
                                cartMenuViewModel.studentUnion_FirstfloorMenuList
                            )
                        } else {
                            cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.clear()
                        }
                    }

                    "학생회관 지하 학식(구시아푸드)" -> {
                        if (checked) {
                            cartMenuViewModel.selected_studentUnion_GusiaMenuList.addAll(
                                cartMenuViewModel.studentUnion_GusiaMenuList
                            )
                        } else {
                            cartMenuViewModel.selected_studentUnion_GusiaMenuList.clear()
                        }
                    }

                    "상허기념도서관 지하 학식(구시아푸드)" -> {
                        if (checked) {
                            cartMenuViewModel.selected_library_GusiaMenuList.addAll(
                                cartMenuViewModel.library_GusiaMenuList
                            )
                        } else {
                            cartMenuViewModel.selected_library_GusiaMenuList.clear()
                        }
                    }
                }
            }
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = "선택삭제",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier.clickable {
                when (placeName) {
                    "학생회관 1층 학식" -> {
                        cartMenuViewModel.studentUnion_FirstfloorMenuList.removeAll(
                            cartMenuViewModel.selected_studentUnion_FirstfloorMenuList
                        )
                        for (menu in cartMenuViewModel.selected_studentUnion_FirstfloorMenuList) {
                            studentUnionMenuViewModel.increaseQuantity(
                                menu.menuItem.category,
                                menu.menuItem.index,
                                menu.menuItem.quantity
                            )
                        }
                        cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.clear()
                    }

                    "학생회관 지하 학식(구시아푸드)" -> {
                        cartMenuViewModel.studentUnion_GusiaMenuList.removeAll(cartMenuViewModel.selected_studentUnion_GusiaMenuList)
                        for (menu in cartMenuViewModel.selected_studentUnion_GusiaMenuList) {
                            studentUnionMenuViewModel.increaseQuantity(
                                menu.menuItem.category,
                                menu.menuItem.index,
                                menu.menuItem.quantity
                            )
                        }
                        cartMenuViewModel.selected_studentUnion_GusiaMenuList.clear()
                    }

                    "상허기념도서관 지하 학식(구시아푸드)" -> {
                        cartMenuViewModel.library_GusiaMenuList.removeAll(cartMenuViewModel.selected_library_GusiaMenuList)
                        for (menu in cartMenuViewModel.selected_library_GusiaMenuList) {
                            libraryMenuViewModel.increaseQuantity(
                                menu.menuItem.category,
                                menu.menuItem.index,
                                menu.menuItem.quantity
                            )
                        }
                        cartMenuViewModel.selected_library_GusiaMenuList.clear()
                    }
                }
                checked = false // Uncheck all items
            }
        )
        Text(
            text = " | ",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        )
        Text(
            text = "전체삭제",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier
                .padding(0.dp, 0.dp, 15.dp, 0.dp)
                .clickable {
                    when (placeName) {
                        "학생회관 1층 학식" -> {
                            for (menu in cartMenuViewModel.studentUnion_FirstfloorMenuList) {
                                studentUnionMenuViewModel.increaseQuantity(
                                    menu.menuItem.category,
                                    menu.menuItem.index,
                                    menu.menuItem.quantity
                                )   //삭제하면서 다시 수량 늘려주기
                            }
                            cartMenuViewModel.studentUnion_FirstfloorMenuList.clear()
                            cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.clear()
                        }

                        "학생회관 지하 학식(구시아푸드)" -> {
                            for (menu in cartMenuViewModel.studentUnion_GusiaMenuList) {
                                studentUnionMenuViewModel.increaseQuantity(
                                    menu.menuItem.category,
                                    menu.menuItem.index,
                                    menu.menuItem.quantity
                                )
                            }
                            cartMenuViewModel.studentUnion_GusiaMenuList.clear()
                            cartMenuViewModel.selected_studentUnion_GusiaMenuList.clear()
                        }

                        "상허기념도서관 지하 학식(구시아푸드)" -> {
                            for (menu in cartMenuViewModel.library_GusiaMenuList) {
                                libraryMenuViewModel.increaseQuantity(
                                    menu.menuItem.category,
                                    menu.menuItem.index,
                                    menu.menuItem.quantity
                                )
                            }
                            cartMenuViewModel.library_GusiaMenuList.clear()
                            cartMenuViewModel.selected_library_GusiaMenuList.clear()
                        }
                    }
                    checked = false // Uncheck all items
                }
        )
    }
}

// '주문하기' btn
@Composable
fun GoOrderBtn(
    onClick: () -> Unit,
    totalPrice:Int
) {
    Spacer(modifier = Modifier.height(24.dp))
    Box(
        modifier = Modifier
            .width(340.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .background(colorResource(id = R.color.green_65a25b)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "총 ${totalPrice}원 주문하기",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
    }
}





// 장바구니 목록 LazyColumn에 담길 제품 UI
@Composable
fun CartItemUI(
    cartMenuViewModel: CartMenuViewModel,
    libraryMenuViewModel: LibraryMenuViewModel,
    studentUnionMenuViewModel: StudentUnionMenuViewModel,
    placeName: String
) {
    val menuList = when (placeName) {
        "학생회관 1층 학식" -> cartMenuViewModel.studentUnion_FirstfloorMenuList
        "학생회관 지하 학식(구시아푸드)" -> cartMenuViewModel.studentUnion_GusiaMenuList
        "상허기념도서관 지하 학식(구시아푸드)" -> cartMenuViewModel.library_GusiaMenuList
        else -> mutableListOf()
    }

    Column {
        menuList.forEach { menu ->
            var isChecked by remember { mutableStateOf(false) }

            if (cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.contains(menu) ||
                cartMenuViewModel.selected_studentUnion_GusiaMenuList.contains(menu) ||
                cartMenuViewModel.selected_library_GusiaMenuList.contains(menu)
            ) {
                isChecked = true
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Image(
                    painter = painterResource(id = if (isChecked) R.drawable.ic_chk_selected else R.drawable.ic_chk_unselected),
                    contentDescription = "CartItem Checkbox",
                    modifier = Modifier
                        .size(15.dp)
                        .clickable {
                            isChecked = !isChecked
                            if (isChecked) {
                                when (placeName) {
                                    "학생회관 1층 학식" -> {
                                        if (!cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.contains(menu))
                                            cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.add(menu)
                                    }

                                    "학생회관 지하 학식(구시아푸드)" -> {
                                        if (!cartMenuViewModel.selected_studentUnion_GusiaMenuList.contains(menu))
                                            cartMenuViewModel.selected_studentUnion_GusiaMenuList.add(menu)
                                    }

                                    "상허기념도서관 지하 학식(구시아푸드)" -> {
                                        if (!cartMenuViewModel.selected_library_GusiaMenuList.contains(menu))
                                            cartMenuViewModel.selected_library_GusiaMenuList.add(menu)
                                    }
                                }
                            } else {
                                when (placeName) {
                                    "학생회관 1층 학식" -> {
                                        cartMenuViewModel.selected_studentUnion_FirstfloorMenuList.remove(menu)
                                    }

                                    "학생회관 지하 학식(구시아푸드)" -> {
                                        cartMenuViewModel.selected_studentUnion_GusiaMenuList.remove(menu)
                                    }

                                    "상허기념도서관 지하 학식(구시아푸드)" -> {
                                        cartMenuViewModel.selected_library_GusiaMenuList.remove(menu)
                                    }
                                }
                            }
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = menu.menuItem.imageRes),
                    contentDescription = "CartItem Image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Row {
                        Text(
                            text = menu.menuItem.name,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.ic_delete),
                            modifier = Modifier
                                .size(15.dp)
                                .align(Alignment.CenterVertically)
                                .clickable(onClick = {
                                    when (placeName) {
                                        "학생회관 1층 학식" -> {
                                            cartMenuViewModel.studentUnion_FirstfloorMenuList.remove(menu)
                                            studentUnionMenuViewModel.increaseQuantity(
                                                menu.menuItem.category,
                                                menu.menuItem.index,
                                                menu.menuItem.quantity
                                            )
                                        }

                                        "학생회관 지하 학식(구시아푸드)" -> {
                                            cartMenuViewModel.studentUnion_GusiaMenuList.remove(menu)
                                            studentUnionMenuViewModel.increaseQuantity(
                                                menu.menuItem.category,
                                                menu.menuItem.index,
                                                menu.menuItem.quantity
                                            )
                                        }

                                        "상허기념도서관 지하 학식(구시아푸드)" -> {
                                            cartMenuViewModel.library_GusiaMenuList.remove(menu)
                                            libraryMenuViewModel.increaseQuantity(
                                                menu.menuItem.category,
                                                menu.menuItem.index,
                                                menu.menuItem.quantity
                                            )
                                        }
                                    }
                                }),
                            contentDescription = "제품 삭제"
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = menu.size ?: "",
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // 사이드메뉴 가로로 2행 배치
                    val options = menu.optionList ?: emptyList()

                    if(options.isNotEmpty()) {
                        LazyHorizontalGrid(
                            rows = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                        ) {
                            items(options) { option ->
                                Text(
                                    text = option,
                                    fontSize = 13.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "수량: ${menu.menuItem.quantity}",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = menu.menuItem.price,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
            Divider(
                color = colorResource(id = R.color.gray_b3b3b3),
                modifier = Modifier.height(1.dp)
            )
        }
    }
}