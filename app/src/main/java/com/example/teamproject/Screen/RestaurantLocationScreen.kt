package com.example.teamproject.Screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teamproject.Item.LocationItem
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun RestaurantTabPager(
    pagerState: PagerState,
    tabs: List<String>,
    navController: NavHostController
) {

    val coroutineScope = rememberCoroutineScope() // 코루틴 스코프 생성
    val location = Restaurant_Location_init()

    Column {
        // 상단바
        TopAppBar(
            onBackIconClick = { navController.popBackStack() },
            title = "학식",
            titleColor = colorResource(R.color.gold_cf982e),
            onRightIconClick = { navController.navigate(Routes.MyPageMainScreen.route) }, // 추후 마이페이지로 이동 가능하도록 구현
            rightIconImgId = R.drawable.profile
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(), // 화면 전체 너비
            color = Color.LightGray, // 테두리 색상
            thickness = 1.dp // 테두리 두께
        )
        // 탭 구현
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = colorResource(id = R.color.gold_cf982e), // 인디케이터 색상 변경
                )
            },
            divider = { },
            containerColor = Color.White, // 배경색 설정
            contentColor = Color.Black
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    modifier = Modifier
                        .width(200.dp)  // 탭의 가로 크기
                        .height(50.dp)
                        , // 탭의 세로 크기
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
                    .padding(5.dp)

            ) {
                // 페이지별 컨텐츠
                when (page) {
                    1 -> MapScreen(navController = navController, location)
                    0 -> ListScreen(navController = navController, location)

                }
            }
        }
    }
}

fun Restaurant_Location_init(): List<LocationItem> {
    val location: List<LocationItem> = listOf(
        LocationItem(
            "학생회관 1층 학식",
            "서울 광진구 능동로 120 1층(화양동)",
            37.5418772,
            127.0782087,
            Routes.StudentUnionFirstfloor.route
        ),
        LocationItem(
            "학생회관 지하 학식 (구시아푸드)",
            "서울 광진구 능동로 120 지하 1층(화양동)",
            37.5418772,
            127.0782087,
            Routes.StudentUnionGusia.route
        ),
        LocationItem(
            "상허기념도서관 지하 학식 (구시아푸드)",
            "서울 광진구 능동로 120 지하 1층(화양동)",
            37.5419226,
            127.0737408,
            Routes.LibraryGusia.route
        ),
    )
    return location
}

@Composable
fun RestaurantLocationScreen(navController: NavHostController) {
    val tabs = listOf("리스트로 보기","지도로 보기")
    val pagerState = rememberPagerState {
        tabs.size
    }

    Column {
        RestaurantTabPager(
            pagerState = pagerState,
            tabs = tabs,
            navController = navController
        )
    }
}