package com.example.teamproject

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.teamproject.Component.MenuGrid

@Composable
fun BabScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "소금 삼겹 덮밥","3500"),
                MenuItem(R.drawable.img2, "삼겹 양념 덮밥","3500"),
                MenuItem(R.drawable.img3, "직화 구이 덮밥","3500"),
                MenuItem(R.drawable.img3, "참치 마요 덮밥","3500")
            )
        )
    }
}

@Composable
fun PopoScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "420 쌀국수","4200"),
                MenuItem(R.drawable.img2, "차돌 쌀국수","5500"),
                MenuItem(R.drawable.img1, "마라 쌀국수","6200"),
                MenuItem(R.drawable.img3, "직화구이 쌀국수","6000"),
                MenuItem(R.drawable.img1, "분짜","6300"),
            )
        )
    }
}

@Composable
fun DonggasScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "왕돈가스","6300"),
                MenuItem(R.drawable.img2, "칼빔면","4500"),
                MenuItem(R.drawable.img3, "카레라이스","5200"),
                MenuItem(R.drawable.img3, "돈비세트","7800"),
                MenuItem(R.drawable.img3, "돈카세트","8500"),
            )
        )
    }
}

@Composable
fun GookbabScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "물냉면","6000"),
                MenuItem(R.drawable.img2, "비빔냉면","6000"),
                MenuItem(R.drawable.img3, "수육국밥","5900"),
                MenuItem(R.drawable.img3, "얼큰국밥","6300"),
                MenuItem(R.drawable.img1, "맑은 순댓국밥","6200")
            )
        )
    }
}


@Composable
fun BoonsikScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "황소라면","3500"),
                MenuItem(R.drawable.img2, "건대떡볶이","3500"),
                MenuItem(R.drawable.img3, "와우라볶이","5500"),
                MenuItem(R.drawable.img3, "성적순대","4000"),
                MenuItem(R.drawable.img1, "폭풍튀김","4000"),
                MenuItem(R.drawable.img2, "장학금주먹밥","2500"),
                MenuItem(R.drawable.img3, "날치알주먹밥","3000"),
                MenuItem(R.drawable.img1, "라면+주먹밥","5500"),
                MenuItem(R.drawable.img2, "떡순이","4500"),
                MenuItem(R.drawable.img3, "떡튀기","4500"),
                MenuItem(R.drawable.img1, "떡튀순","7000")
            )
        )
    }
}

@Composable
fun MaraScreen() {
    Column {
        MenuGrid(
            menuItems = listOf(
                MenuItem(R.drawable.img1, "마라탕 기본","5900"),
                MenuItem(R.drawable.img2, "소고기마라탕","7400"),
                MenuItem(R.drawable.img3, "부대마라탕","7800"),
                MenuItem(R.drawable.img3, "몽땅마라탕","8800"),
                MenuItem(R.drawable.img1, "미니꼬바로우","3800"),
                MenuItem(R.drawable.img2, "만두튀김","1800"),
                MenuItem(R.drawable.img3, "K 짜장","4500"),
                MenuItem(R.drawable.img2, "마라 짜장","5900")
            )
        )
    }
}