package com.example.teamproject.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R
import kotlinx.coroutines.launch

class LibraryMenuViewModel : ViewModel() {
    val BabItems = mutableStateListOf(
        MenuItem(R.drawable.saltbab, "소금 삼겹 덮밥", "3500", 10, "Bab", 0),
        MenuItem(R.drawable.redbab, "삼겹 양념 덮밥", "3500", 3, "Bab", 1),
        MenuItem(R.drawable.firebab, "직화 구이 덮밥", "3500", 20, "Bab", 2),
        MenuItem(R.drawable.tunabab, "참치 마요 덮밥", "3500", 2, "Bab", 3)
    )

    val PopoItems = mutableStateListOf(
        MenuItem(R.drawable.basicpopo, "420 쌀국수", "4200", 15, "Popo", 0),
        MenuItem(R.drawable.chadolpopo, "차돌 쌀국수", "5500", 11, "Popo", 1),
        MenuItem(R.drawable.marapopo, "마라 쌀국수", "6200", 3, "Popo", 2),
        MenuItem(R.drawable.firepopo, "직화구이 쌀국수", "6000", 13, "Popo", 3),
        MenuItem(R.drawable.wantang, "완탕 쌀국수", "6000", 14, "Popo", 4),
    )

    val DonggasItems = mutableStateListOf(
        MenuItem(R.drawable.donggas, "120 돈가스", "5800", 5, "Donggas", 0),
        MenuItem(R.drawable.donggas, "170 돈가스", "6800", 10,"Donggas", 1),
        MenuItem(R.drawable.calbim, "칼빔면", "4500", 19,"Donggas", 2),
        MenuItem(R.drawable.curry, "카레라이스", "5200", 17,"Donggas", 3),
        MenuItem(R.drawable.donbi, "돈비세트", "7800", 2,"Donggas", 4),
        MenuItem(R.drawable.donka, "돈카세트", "8500", 1,"Donggas", 5),
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
            }
        }
    }




}
