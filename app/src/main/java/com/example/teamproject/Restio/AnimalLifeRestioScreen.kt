package com.example.teamproject.Restio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.teamproject.Component.KioskMenuItem
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R
import com.example.teamproject.Screen.TopAppBar
import com.example.teamproject.navigation.Routes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun AnimalLifeRestioScreen(title: String, navController: NavHostController, viewModel: AnimalLifeRestioMenuViewModel = viewModel()) {
    val categories = listOf(
        "커피 HOT",
        "커피 ICE" ,
        "라떼류",
        "에이드|스무디",
        "티",
        "주스|병음료",
        "오늘의 우유",
        "샌드위치"
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    var selectedProduct by remember { mutableStateOf<MenuItem?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                onBackIconClick = { navController.popBackStack() },
                title = title,
                titleColor = Color.Black,
                onRightIconClick = { navController.navigate("cart_screen/레스티오 동물생명과학관") },
                rightIconImgId = R.drawable.cart
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // 상단 탭
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    edgePadding = 0.dp,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier
                                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                .height(4.dp),
                            color = colorResource(id = R.color.green_066b3f)
                        )
                    }
                ) {
                    categories.forEachIndexed { index, category ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = { Text(text = category,
                                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                            ) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 제품 페이지
                HorizontalPager(
                    count = categories.size,
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 10.dp),
                        contentPadding = PaddingValues(vertical = 30.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        viewModel.productsMap[categories[page]]?.let { products ->
                            items(products) { product ->
//                                ProductCard(product) {
//                                    navController.navigate(
//                                        "restioPay/${title}/${it.name}/${it.price}/${it.imageRes}/${it.category}"
//                                    )
//                                }
                                KioskMenuItem(
                                    imageRes = product.imageRes,
                                    menuName = product.name,
                                    menuPrice = product.price,
                                    initialQuantity = product.quantity,
                                    place = ""
                                    ) {
                                    navController.navigate(
                                        "restioPay/${title}/${product.name}/${product.price}/${product.imageRes}/${product.category}"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
