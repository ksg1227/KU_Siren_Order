package com.example.teamproject.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamproject.MenuItem
import com.example.teamproject.R
import kotlinx.coroutines.launch

class StudentUnionMenuViewModel : ViewModel() {
    val BabItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "소금 삼겹 덮밥", "3500", 1),
        MenuItem(R.drawable.img2, "삼겹 양념 덮밥", "3500", 1),
        MenuItem(R.drawable.img3, "참치 마요 덮밥", "3500", 1)
    )

    val PopoItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "420 쌀국수", "4200", 1),
        MenuItem(R.drawable.img2, "차돌 쌀국수", "5500", 1),
        MenuItem(R.drawable.img1, "마라 쌀국수", "6200", 1),
        MenuItem(R.drawable.img3, "직화구이 쌀국수", "6000", 1),
        MenuItem(R.drawable.img1, "분짜", "6300", 1),
    )

    val DonggasItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "왕돈가스", "6300", 1),
        MenuItem(R.drawable.img2, "칼빔면", "4500", 1),
        MenuItem(R.drawable.img3, "카레라이스", "5200", 1),
        MenuItem(R.drawable.img3, "돈비세트", "7800", 1),
        MenuItem(R.drawable.img3, "돈카세트", "8500", 1),
    )

    val GookbabItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "물냉면", "6000", 1),
        MenuItem(R.drawable.img2, "비빔냉면", "6000", 1),
        MenuItem(R.drawable.img3, "수육국밥", "5900", 1),
        MenuItem(R.drawable.img3, "얼큰국밥", "6300", 1),
        MenuItem(R.drawable.img1, "맑은 순댓국밥", "6200", 1)
    )

    val BoonsikItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "황소라면", "3500", 1),
        MenuItem(R.drawable.img2, "건대떡볶이", "3500", 1),
        MenuItem(R.drawable.img3, "와우라볶이", "5500", 1),
        MenuItem(R.drawable.img3, "성적순대", "4000", 1),
        MenuItem(R.drawable.img1, "폭풍튀김", "4000", 1),
        MenuItem(R.drawable.img2, "장학금주먹밥", "2500", 1),
        MenuItem(R.drawable.img3, "날치알주먹밥", "3000", 1),
        MenuItem(R.drawable.img1, "라면+주먹밥", "5500", 1),
        MenuItem(R.drawable.img2, "떡순이", "4500", 1),
        MenuItem(R.drawable.img3, "떡튀기", "4500", 1),
        MenuItem(R.drawable.img1, "떡튀순", "7000", 1)
    )

    val MaraItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "마라탕 기본", "5900", 1),
        MenuItem(R.drawable.img2, "소고기마라탕", "7400", 1),
        MenuItem(R.drawable.img3, "부대마라탕", "7800", 1),
        MenuItem(R.drawable.img3, "몽땅마라탕", "8800", 1),
        MenuItem(R.drawable.img1, "미니꼬바로우", "3800", 1),
        MenuItem(R.drawable.img2, "만두튀김", "1800", 1),
        MenuItem(R.drawable.img3, "K 짜장", "4500", 1),
        MenuItem(R.drawable.img2, "마라 짜장", "5900", 1)
    )


    fun decreaseBabQuantity(index: Int) {
        viewModelScope.launch {
            if (BabItems[index].quantity > 0) {
                BabItems[index].quantity -= 1
            }
        }
    }

    fun decreasePopoQuantity(index: Int) {
        viewModelScope.launch {
            if (PopoItems[index].quantity > 0) {
                PopoItems[index].quantity -= 1
            }
        }
    }

    fun decreaseDonggasQuantity(index: Int) {
        viewModelScope.launch {
            if (DonggasItems[index].quantity > 0) {
                DonggasItems[index].quantity -= 1
            }
        }
    }

    fun decreaseGookbabQuantity(index: Int) {
        viewModelScope.launch {
            if (GookbabItems[index].quantity > 0) {
                GookbabItems[index].quantity -= 1
            }
        }
    }

    fun decreaseBoonsikQuantity(index: Int) {
        viewModelScope.launch {
            if (BoonsikItems[index].quantity > 0) {
                BoonsikItems[index].quantity -= 1
            }
        }
    }

    fun decreaseMaraQuantity(index: Int) {
        viewModelScope.launch {
            if (MaraItems[index].quantity > 0) {
                MaraItems[index].quantity -= 1
            }
        }
    }


}