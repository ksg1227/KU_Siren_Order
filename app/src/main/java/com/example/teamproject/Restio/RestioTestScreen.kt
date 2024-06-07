//package com.example.teamproject.Restio
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.teamproject.R
//
//@Composable
//fun EngineeringRestioScreen() {
//    val categories =
//        listOf("커피 HOT", "커피 ICE", "티라떼/아이스티", "에이드/티백", "레스티오/스무디/과일주스", "베이커리", "샌드위치/핫도그")
//    var selectedCategory by remember { mutableStateOf(categories.first()) }
//
//    val productsHot = listOf(
//        Product("에스프레소", "2,600원", R.drawable.konkuk),
//        Product("아메리카노", "2,600원", R.drawable.konkuk),
//        Product("카페라떼", "2,800원", R.drawable.konkuk),
//        Product("카푸치노 (only hot)", "2,800원", R.drawable.konkuk),
//        Product("캬라멜마끼아또", "3,200원", R.drawable.konkuk),
//        Product("헤이즐넛라떼", "3,000원", R.drawable.konkuk),
//        Product("바닐라라떼", "3,000원", R.drawable.konkuk),
//        Product("카페모카", "3,200원", R.drawable.konkuk),
//        Product("진저라떼", "3,000원", R.drawable.konkuk),
//        Product("아이스 아메리카노", "3,000원", R.drawable.konkuk),
//        Product("아이스 카페라떼", "3,200원", R.drawable.konkuk),
//        Product("아이스 카푸치노", "3,200원", R.drawable.konkuk),
//        Product("아이스 카라멜 마끼아또", "3,500원", R.drawable.konkuk),
//        Product("아이스 바닐라라떼", "3,200원", R.drawable.konkuk),
//        Product("아이스 카페모카", "3,500원", R.drawable.konkuk)
//    )
//
//    val productsIce = listOf(
//        Product("아이스 아메리카노", "3,000원", R.drawable.konkuk),
//        Product("아이스 카페라떼", "3,200원", R.drawable.konkuk),
//        Product("아이스 카푸치노", "3,200원", R.drawable.konkuk),
//        Product("아이스 카라멜 마끼아또", "3,500원", R.drawable.konkuk),
//        Product("아이스 바닐라라떼", "3,200원", R.drawable.konkuk),
//        Product("아이스 카페모카", "3,500원", R.drawable.konkuk)
//    )
//
//    val productsMap = mapOf(
//        "커피 HOT" to productsHot,
//        "커피 ICE" to productsIce
//        // Add more categories and products here
//    )
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            com.example.teamproject.Screen.TopAppBar(
//                onBackIconClick = { /*TODO*/ },
//                title = "학식 / 레스티오",
//                titleColor = Color.Black,
//                onRightIconClick = { /*TODO*/ },
//                rightIconImgId = null
//            )
//
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                // Header with Tabs
//                ScrollableTabRow(
//                    selectedTabIndex = categories.indexOf(selectedCategory),
//                    edgePadding = 0.dp
//                ) {
//                    categories.forEachIndexed { index, category ->
//                        Tab(
//                            selected = selectedCategory == category,
//                            onClick = { selectedCategory = category },
//                            text = { Text(text = category) }
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Product Grid
//                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//                    productsMap[selectedCategory]?.chunked(3)?.forEach { rowProducts ->
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            rowProducts.forEach { product ->
//                                ProductCard(product)
//                            }
//                            repeat(3 - rowProducts.size) {
//                                Spacer(modifier = Modifier.width(120.dp))
//                            }
//                        }
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
//                }
//            }
//
//            // Footer with Order Summary
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Button(
//                    onClick = { /* 장바구니 */ },
//                    shape = RoundedCornerShape(50)
//                ) {
//                    Text(text = "장바구니", fontSize = 14.sp)
//                }
//                Column(horizontalAlignment = Alignment.End) {
//                    Text(text = "선택한 상품 개수")
//                    Text(text = "원 결제예정")
//                }
//                Button(
//                    onClick = { /* 결제 처리 */ },
//                    shape = RoundedCornerShape(50)
//                ) {
//                    Text(text = "결제하기", fontSize = 14.sp)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ProductCard(product: Product) {
//    Column(
//        modifier = Modifier
//            .width(130.dp)
//            .height(200.dp)
//            .padding(8.dp)
//            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
//            .clickable { /* 상품 선택 처리 */ },
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(id = product.imageRes),
//            contentDescription = product.name,
//            modifier = Modifier.size(80.dp)
//        )
//        Spacer(modifier = Modifier.height(4.dp)) // Image와 Text 사이에 약간의 간격 추가
//        Text(
//            text = product.name,
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Bold,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis
//        )
//        Spacer(modifier = Modifier.height(4.dp)) // Text 사이에 약간의 간격 추가
//        Text(
//            text = product.price,
//            fontSize = 12.sp,
//            color = Color.Gray
//        )
//    }
//}
//
//data class Product(
//    val name: String,
//    val price: String,
//    val imageRes: Int
//)
