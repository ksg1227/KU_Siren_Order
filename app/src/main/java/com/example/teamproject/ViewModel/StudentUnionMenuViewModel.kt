package com.example.teamproject.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamproject.MenuItem
import com.example.teamproject.R
import kotlinx.coroutines.launch

class StudentUnionMenuViewModel : ViewModel() {
    val BabItems = mutableStateListOf(
        MenuItem(R.drawable.saltbab, "소금 삼겹 덮밥", "3500", 1),
        MenuItem(R.drawable.redbab, "삼겹 양념 덮밥", "3500", 1),
        MenuItem(R.drawable.tunabab, "참치 마요 덮밥", "3500", 1)
    )

    val PopoItems = mutableStateListOf(
        MenuItem(R.drawable.basicpopo, "420 쌀국수", "4200", 1),
        MenuItem(R.drawable.chadolpopo, "차돌 쌀국수", "5500", 1),
        MenuItem(R.drawable.marapopo, "마라 쌀국수", "6200", 1),
        MenuItem(R.drawable.firepopo, "직화구이 쌀국수", "6000", 1),
        MenuItem(R.drawable.firepopo, "분짜", "6300", 1),
    )

    val DonggasItems = mutableStateListOf(
        MenuItem(R.drawable.wangdon, "왕돈가스", "6300", 1),
        MenuItem(R.drawable.calbim, "칼빔면", "4500", 1),
        MenuItem(R.drawable.curry, "카레라이스", "5200", 1),
        MenuItem(R.drawable.donbi, "돈비세트", "7800", 1),
        MenuItem(R.drawable.donka, "돈카세트", "8500", 1),
    )

    val GookbabItems = mutableStateListOf(
        MenuItem(R.drawable.wangdon, "물냉면", "6000", 1),
        MenuItem(R.drawable.wangdon, "비빔냉면", "6000", 1),
        MenuItem(R.drawable.meatgook, "수육국밥", "5900", 1),
        MenuItem(R.drawable.spicygook, "얼큰국밥", "6300", 1),
        MenuItem(R.drawable.mildgook, "맑은 순댓국밥", "6200", 1)
    )

    val BoonsikItems = mutableStateListOf(
        MenuItem(R.drawable.curry, "황소라면", "3500", 1),
        MenuItem(R.drawable.curry, "건대떡볶이", "3500", 1),
        MenuItem(R.drawable.curry, "와우라볶이", "5500", 1),
        MenuItem(R.drawable.curry, "성적순대", "4000", 1),
        MenuItem(R.drawable.curry, "폭풍튀김", "4000", 1),
        MenuItem(R.drawable.curry, "장학금주먹밥", "2500", 1),
        MenuItem(R.drawable.curry, "날치알주먹밥", "3000", 1),
        MenuItem(R.drawable.curry, "라면+주먹밥", "5500", 1),
        MenuItem(R.drawable.curry, "떡순이", "4500", 1),
        MenuItem(R.drawable.curry, "떡튀기", "4500", 1),
        MenuItem(R.drawable.curry, "떡튀순", "7000", 1)
    )

    val MaraItems = mutableStateListOf(
        MenuItem(R.drawable.curry, "마라탕 기본", "5900", 1),
        MenuItem(R.drawable.curry, "소고기마라탕", "7400", 1),
        MenuItem(R.drawable.curry, "부대마라탕", "7800", 1),
        MenuItem(R.drawable.curry, "몽땅마라탕", "8800", 1),
        MenuItem(R.drawable.curry, "미니꼬바로우", "3800", 1),
        MenuItem(R.drawable.curry, "만두튀김", "1800", 1),
        MenuItem(R.drawable.curry, "K 짜장", "4500", 1),
        MenuItem(R.drawable.curry, "마라 짜장", "5900", 1)
    )


    fun decreaseQuantity(category: String, index: Int, quantity: Int) {
        viewModelScope.launch {
            when (category) {
                "Bab" -> {
                    if (BabItems[index].quantity >= quantity) {
                        BabItems[index].quantity -= quantity
                    }
                }
                "Popo" -> {
                    if (PopoItems[index].quantity >= quantity) {
                        PopoItems[index].quantity -= quantity
                    }
                }
                "Donggas" -> {
                    if (DonggasItems[index].quantity >= quantity) {
                        DonggasItems[index].quantity -= quantity
                    }
                }
                "Gookbab" -> {
                    if (GookbabItems[index].quantity >= quantity) {
                        GookbabItems[index].quantity -= quantity
                    }
                }
                "Boonsik" -> {
                    if (BoonsikItems[index].quantity >= quantity) {
                        BoonsikItems[index].quantity -= quantity
                    }
                }
                "Mara" -> {
                    if (MaraItems[index].quantity >= quantity) {
                        MaraItems[index].quantity -= quantity
                    }
                }
            }
        }
    }


}