package com.example.teamproject.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.teamproject.Item.LocationItem
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes

@Composable
fun ListScreen(navController: NavController, location: List<LocationItem>, color: Int) {
    Column(modifier = Modifier.padding(5.dp)) {
        location.forEach {
            ListItem(location = it, navController = navController, color)
        }
    }
}

@Composable
fun ListItem(location: LocationItem, navController: NavController, color: Int) {
    Column(
        modifier = Modifier
            .clickable {
                navController.navigate(location.route)
            }
            .fillMaxWidth()
            .padding(5.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.gray_e6e6e6),
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = colorResource(id = color), shape = RoundedCornerShape(10.dp))
            .padding(start = 15.dp, bottom = 10.dp, end = 15.dp, top = 10.dp)

    )
    {
        Text(
            text = location.locationName,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            color = Color.Black,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = location.locationAddress,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            color = Color(0xFF444444),
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = location.hours,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            color = Color(0xFF444444),
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Preview
@Composable
private fun preview() {
    val navController = rememberNavController()
    val loaction = Restio_Location_init()
    ListScreen(navController = navController, location = loaction, R.color.green_0A1009)
    fun Restio_Location_init(): List<LocationItem> {
        val location: List<LocationItem> = listOf(
            LocationItem(
                "동물생명과학관",
                "서울 광진구 능동로 120 1층(화양동)",
                37.5403664,
                127.0743614,
                Routes.LibraryGusia.route,
                "월~금 09:00 ~ 18:00"
            ),
            LocationItem(
                "상허기념도서관",
                "서울 광진구 능동로 120 3층(화양동)",
                37.5419226,
                127.0737408,
                Routes.LibraryGusia.route,
                "월~금 09:00 ~ 18:00"
            ),
            LocationItem(
                "경영관",
                "서울 광진구 능동로 120 1층(화양동)",
                37.5442615,
                127.0760717,
                Routes.LibraryGusia.route,
                "월~금 08:30 ~ 18:00"
            ),
            LocationItem(
                "공학관",
                "서울 광진구 능동로 120 건국대학교 공학관 1층(화양동)",
                37.541635,
                127.0787904,
                Routes.LibraryGusia.route,
                "월~금 08:30 ~ 18:00"
            ),
            LocationItem(
                "산학협동관",
                "서울 광진구 능동로 120 1층(화양동)",
                37.5396663,
                127.0732309,
                Routes.LibraryGusia.route,
                "월~금 08:30 ~ 19:00"
            )
        )
        return location
    }
}

@Preview
@Composable
private fun di() {
    val nav = rememberNavController()
    fun Restaurant_Location_init(): List<LocationItem> {
        val location: List<LocationItem> = listOf(
            LocationItem(
                "학생회관 1층 학식",
                "서울 광진구 능동로 120 1층(화양동)",
                37.5418772,
                127.0782087,
                Routes.StudentUnionFirstfloor.route,
                "월~금 10:30 ~ 19:00"
            ),
            LocationItem(
                "학생회관 지하 학식 (구시아푸드)",
                "서울 광진구 능동로 120 지하 1층(화양동)",
                37.5418772,
                127.0782087,
                Routes.StudentUnionGusia.route,
                "월~금 10:30 ~ 19:00"
            ),
            LocationItem(
                "상허기념도서관 지하 학식 (구시아푸드)",
                "서울 광진구 능동로 120 지하 1층(화양동)",
                37.5419226,
                127.0737408,
                Routes.LibraryGusia.route,
                "월~금 10:30 ~ 19:00 / 토 10:30 ~ 15:00"
            ),
        )
        return location
    }
    ListScreen(navController = nav, location = Restaurant_Location_init(), R.color.gold_cf982e)
}