package com.example.teamproject.Screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.teamproject.Item.LocationItem
import com.example.teamproject.navigation.Routes

@Composable
fun ListScreen(navController: NavController, location: List<LocationItem>) {
    Column {
        location.forEach {
            ListItem(location = it, navController = navController)
        }
    }
}

@Composable
fun ListItem(location: LocationItem, navController: NavController) {
    Column(
        modifier = Modifier
            .clickable {
                navController.navigate(location.route)
            }
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RectangleShape
            )
            .padding(start = 15.dp, bottom = 10.dp, end = 15.dp)

    )
    {
        Text(
            text = location.locationName,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = location.locationAddress,
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier.padding(5.dp)
        )

    }
}

@Preview
@Composable
private fun preview() {
    val navController = rememberNavController()
    val loaction = Restio_Location_init()
    ListScreen(navController = navController, location = loaction)
    fun Restio_Location_init(): List<LocationItem> {
        val location: List<LocationItem> = listOf(
            LocationItem(
                "동물생명과학관",
                "서울 광진구 능동로 120 1층(화양동)",
                37.5403664,
                127.0743614,
                Routes.LibraryGusia.route
            ),
            LocationItem(
                "상허기념도서관",
                "서울 광진구 능동로 120 3층(화양동)",
                37.5419226,
                127.0737408,
                Routes.LibraryGusia.route
            ),
            LocationItem(
                "경영관",
                "서울 광진구 능동로 120 1층(화양동)",
                37.5442615,
                127.0760717,
                Routes.LibraryGusia.route
            ),
            LocationItem(
                "공학관",
                "서울 광진구 능동로 120 건국대학교 공학관 1층(화양동)",
                37.541635,
                127.0787904,
                Routes.LibraryGusia.route
            ),
            LocationItem(
                "산학협동관",
                "서울 광진구 능동로 120 1층(화양동)",
                37.5396663,
                127.0732309,
                Routes.LibraryGusia.route
            )
        )
        return location
    }
}
