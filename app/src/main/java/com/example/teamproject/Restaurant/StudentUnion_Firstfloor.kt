package com.example.teamproject.Restaurant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teamproject.R
import com.example.teamproject.Screen.StudentUnion_FirstfloorMenuScreen
import com.example.teamproject.Screen.TopAppBar
import com.example.teamproject.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun CustomTabPager3(pagerState: PagerState, tabs: List<String>, navController : NavHostController) {

    val coroutineScope = rememberCoroutineScope() // 코루틴 스코프 생성

    Column {
//        Text(text = "202011260 김상균")
        // 탭 구현
        TopAppBar(
            onBackIconClick = { navController.popBackStack() },
            title = "학생회관 1층 학식",
            titleColor = Color.Black,
            onRightIconClick = { navController.navigate(Routes.Cart.route) },
            rightIconImgId = R.drawable.konkuk
        )

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Color.Black, // 인디케이터 색상 변경
                )
            },
            divider = {}, // 빈 컴포저블을 지정하여 경계선 제거
            containerColor = Color.White, // 배경색 설정
            contentColor = Color.Black,
            edgePadding = 0.dp, // 패딩 설정
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    modifier = Modifier
                        .width(420.dp)  // 탭의 가로 크기
                        .height(50.dp), // 탭의 세로 크기
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(), // 화면 전체 너비
            color = Color.LightGray, // 테두리 색상
            thickness = 1.dp // 테두리 두께
        )

        // 뷰페이저 구현
        HorizontalPager(
            state = pagerState
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 페이지별 컨텐츠
                when (page) {
                    0 -> StudentUnion_FirstfloorMenuScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun StudentUnion_FirstfloorScreen(navController: NavHostController) {
    val tabs = listOf("1층 학생 식당")
    val pagerState = rememberPagerState {
        tabs.size
    }

    Column {
        CustomTabPager3(
            pagerState = pagerState,
            tabs = tabs,
            navController = navController
        )
    }
}