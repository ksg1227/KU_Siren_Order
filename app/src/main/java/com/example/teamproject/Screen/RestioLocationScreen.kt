package com.example.teamproject.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teamproject.Item.LocationItem
import com.example.teamproject.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun RestioTabPager(pagerState: PagerState, tabs: List<String>, navController:NavHostController) {

    val coroutineScope = rememberCoroutineScope() // 코루틴 스코프 생성
    val location = Restio_Location_init()

    Column {
        // 탭 구현
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = Color.Green, // 인디케이터 색상 변경
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
                        .width(200.dp)  // 탭의 가로 크기
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
                .fillMaxWidth()
                .padding(20.dp), // 화면 전체 너비
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
                    .background(Color.White)
            ) {
                // 페이지별 컨텐츠
                when (page) {
                    0 -> MapScreen(navController = navController,location)
                    1 -> ListScreen(navController = navController,location)

                }
            }
        }
    }
}
fun Restio_Location_init(): List<LocationItem> {
    val location: List<LocationItem> = listOf(
        LocationItem("동물생명과학관", "서울 광진구 능동로 120 1층(화양동)", 37.5403664, 127.0743614,Routes.LibraryGusia.route),
        LocationItem("상허기념도서관", "서울 광진구 능동로 120 3층(화양동)", 37.5419226, 127.0737408,Routes.LibraryGusia.route),
        LocationItem("경영관", "서울 광진구 능동로 120 1층(화양동)", 37.5442615, 127.0760717,Routes.LibraryGusia.route),
        LocationItem("공학관", "서울 광진구 능동로 120 건국대학교 공학관 1층(화양동)", 37.541635, 127.0787904,Routes.LibraryGusia.route),
        LocationItem("산학협동관", "서울 광진구 능동로 120 1층(화양동)", 37.5396663, 127.0732309,Routes.LibraryGusia.route)
    )
    return location
}

@Composable
fun RestioLocationScreen(navController: NavHostController) {
    val tabs = listOf("지도로 보기", "리스트로 보기")
    val pagerState = rememberPagerState {
        tabs.size
    }

    Column {
        RestioTabPager(
            pagerState = pagerState,
            tabs = tabs,
            navController = navController
        )
    }
}