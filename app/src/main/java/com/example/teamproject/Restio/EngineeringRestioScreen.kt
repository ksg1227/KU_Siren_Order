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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teamproject.R
import com.example.teamproject.Screen.TopAppBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

data class Product(
    val name: String,
    val price: String,
    val imageRes: Int,
    val category: String
)

@Composable
fun EngineeringRestioScreen() {
    val categories = listOf("커피 HOT", "커피 ICE", "티라떼/아이스티", "에이드/티백", "레스치노/스무디/과일주스", "베이커리", "샌드위치/핫도그")

    val productsHot = listOf(
        Product("에스프레소", "2,600원", R.drawable.konkuk, "커피 HOT"),
        Product("아메리카노", "2,600원", R.drawable.konkuk, "커피 HOT"),
        Product("카페라떼", "2,800원", R.drawable.konkuk, "커피 HOT"),
        Product("카푸치노", "2,800원", R.drawable.konkuk, "커피 HOT"),
        Product("캬라멜마끼아또", "3,200원", R.drawable.konkuk, "커피 HOT"),
        Product("헤이즐넛라떼", "3,000원", R.drawable.konkuk, "커피 HOT"),
        Product("바닐라라떼", "3,000원", R.drawable.konkuk, "커피 HOT"),
        Product("카페모카", "3,200원", R.drawable.konkuk, "커피 HOT"),
        Product("진저라떼", "3,000원", R.drawable.konkuk, "커피 HOT")
    )

    // 커피 ICE
    val productsIce = listOf(
        Product("아이스 아메리카노", "3,000원", R.drawable.konkuk, "커피 ICE"),
        Product("아이스 카페라떼", "3,200원", R.drawable.konkuk, "커피 ICE"),
        Product("아이스 카푸치노", "3,200원", R.drawable.konkuk, "커피 ICE"),
        Product("아이스 카라멜 마끼아또", "3,500원", R.drawable.konkuk, "커피 ICE"),
        Product("아이스 바닐라라떼", "3,200원", R.drawable.konkuk, "커피 ICE"),
        Product("아이스 카페모카", "3,500원", R.drawable.konkuk, "커피 ICE")
    )

    // 티라떼/아이스티
    val productsTea = listOf(
        Product("초코라떼", "2,600원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("그린티라떼", "3,000원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("홍차라떼", "3,000원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("고구마라떼", "3,000원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("복숭아아이스티", "2,800원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("아이스 초코라떼", "2,900원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("아이스 그린티라떼", "3,400원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("아이스 홍차라떼", "3,400원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("아이스 고구마라떼", "3,400원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("자몽차", "2,000원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("레몬차", "2,000원", R.drawable.konkuk, "티라떼/아이스티"),
        Product("유자차", "2,000원", R.drawable.konkuk, "티라떼/아이스티")
    )

    // 에이드/티백
    val productsAdeTeabag = listOf(
        Product("카모마일", "2,600원", R.drawable.konkuk, "에이드/티백"),
        Product("페퍼민트", "2,600원", R.drawable.konkuk, "에이드/티백"),
        Product("녹차", "2,600원", R.drawable.konkuk, "에이드/티백"),
        Product("홍차", "2,600원", R.drawable.konkuk, "에이드/티백"),
        Product("유자민트티", "2,900원", R.drawable.konkuk, "에이드/티백"),
        Product("블루베리에이드", "2,800원", R.drawable.konkuk, "에이드/티백"),
        Product("청포도에이드", "2,800원", R.drawable.konkuk, "에이드/티백"),
        Product("자몽에이드", "2,800원", R.drawable.konkuk, "에이드/티백"),
        Product("레몬에이드", "2,800원", R.drawable.konkuk, "에이드/티백")
    )

    // 레스치노/스무디/과일주스
    val productsZ = listOf(
        Product("딸기", "3,400원", R.drawable.konkuk, "레스치노/스무디/과일주스"),
        Product("블루베리", "3,400원", R.drawable.konkuk, "레스치노/스무디/과일주스"),
        Product("망고", "3,400원", R.drawable.konkuk, "레스치노/스무디/과일주스"),
        Product("플레인요거트", "3,600원", R.drawable.konkuk, "레스치노/스무디/과일주스"),
        Product("딸기요거트", "3,600원", R.drawable.konkuk, "레스치노/스무디/과일주스"),
        Product("블루베리요거트", "3,600원", R.drawable.konkuk, "레스치노/스무디/과일주스"),
        Product("레몬요거트", "3,600원", R.drawable.konkuk, "레스치노/스무디/과일주스")
    )

    // 베이커리
    val bakery = listOf(
        Product("브라우니쿠키", "3,200원", R.drawable.konkuk, "베이커리"),
        Product("체커쿠키", "3,200원", R.drawable.konkuk, "베이커리"),
        Product("초코칩쿠키", "3,200원", R.drawable.konkuk, "베이커리"),
        Product("초코칩머핀", "2,200원", R.drawable.konkuk, "베이커리"),
        Product("치즈머핀", "2,200원", R.drawable.konkuk, "베이커리"),
        Product("블루베리머핀", "2,200원", R.drawable.konkuk, "베이커리"),
        Product("플레인와플", "2,200원", R.drawable.konkuk, "베이커리")
    )

    // 샌드위치/핫도그
    val productsSandHot = listOf(
        Product("레스티오치아바타", "3,100원", R.drawable.konkuk, "샌드위치/핫도그"),
        Product("카야샌드위치", "2,200원", R.drawable.konkuk, "샌드위치/핫도그"),
        Product("햄치즈샌드위치", "2,600원", R.drawable.konkuk, "샌드위치/핫도그"),
        Product("오리지날핫도그", "3,200원", R.drawable.konkuk, "샌드위치/핫도그"),
        Product("칠리핫도그", "3,200원", R.drawable.konkuk, "샌드위치/핫도그")
    )

    val productsMap = mapOf(
        "커피 HOT" to productsHot,
        "커피 ICE" to productsIce,
        "티라떼/아이스티" to productsTea,
        "에이드/티백" to productsAdeTeabag,
        "레스치노/스무디/과일주스" to productsZ,
        "베이커리" to bakery,
        "샌드위치/핫도그" to productsSandHot
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (selectedProduct != null) {
            RestioPayScreen(selectedProduct!!, onClose = { selectedProduct = null })
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopAppBar(
                    onBackIconClick = { /*TODO*/ },
                    title = "공학관 레스티오",
                    titleColor = Color.Black,
                    onRightIconClick = { /*TODO*/ },
                    rightIconImgId = null
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // 상단 탭
                    ScrollableTabRow(
                        selectedTabIndex = pagerState.currentPage,
                        edgePadding = 0.dp
                    ) {
                        categories.forEachIndexed { index, category ->
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                text = { Text(text = category) }
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
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            productsMap[categories[page]]?.let { products ->
                                items(products) { product ->
                                    ProductCard(product) { selectedProduct = it }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
            .clickable { onClick(product) }
            .padding(8.dp)
            .width(120.dp)
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = product.price,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}