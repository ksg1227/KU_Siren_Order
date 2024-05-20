package com.example.teamproject

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.teamproject.Component.MenuGrid

@Composable
fun BabScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "소금 삼겹 덮밥"),
                MenuItem(R.drawable.img2, "삼겹 양념 덮밥"),
                MenuItem(R.drawable.img3, "직화 구이 덮밥"),
                MenuItem(R.drawable.img3, "참치 마요 덮밥")
            )
        )
    }
}

@Composable
fun PopoScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "양지 쌀국수"),
                MenuItem(R.drawable.img2, "420 쌀국수"),
                MenuItem(R.drawable.img3, "차돌 쌀국수"),
                MenuItem(R.drawable.img3, "마라 쌀국수"),
                MenuItem(R.drawable.img1, "몰러 쌀국수"),
            )
        )
    }
}

@Composable
fun DonggasScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "왕돈까스"),
                MenuItem(R.drawable.img2, "칼빔면"),
                MenuItem(R.drawable.img3, "카레라이스"),
                MenuItem(R.drawable.img3, "돈까스 + 카레")
            )
        )
    }
}

@Composable
fun GookbabScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "국밥1"),
                MenuItem(R.drawable.img2, "국밥2"),
                MenuItem(R.drawable.img3, "국밥3"),
                MenuItem(R.drawable.img3, "국밥4"),
                MenuItem(R.drawable.img1, "국밥5"),
                MenuItem(R.drawable.img2, "국밥6"),
                MenuItem(R.drawable.img3, "국밥7")
            )
        )
    }
}


@Composable
fun BoonsikScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "분식1"),
                MenuItem(R.drawable.img2, "분식2"),
                MenuItem(R.drawable.img3, "분식3"),
                MenuItem(R.drawable.img3, "분식4"),
                MenuItem(R.drawable.img1, "분식5"),
                MenuItem(R.drawable.img2, "분식6")
            )
        )
    }
}

@Composable
fun MaraScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "마라1"),
                MenuItem(R.drawable.img2, "마라2"),
                MenuItem(R.drawable.img3, "마라3"),
                MenuItem(R.drawable.img3, "마라4"),
                MenuItem(R.drawable.img1, "마라5"),
                MenuItem(R.drawable.img2, "마라6"),
                MenuItem(R.drawable.img3, "마라7")
            )
        )
    }
}