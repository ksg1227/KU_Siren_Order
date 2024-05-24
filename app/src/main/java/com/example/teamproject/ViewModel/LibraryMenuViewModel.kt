package com.example.teamproject.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamproject.MenuItem
import com.example.teamproject.R
import kotlinx.coroutines.launch

class LibraryMenuViewModel : ViewModel() {
    val BabItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "소금 삼겹 덮밥", "3500", 10),
        MenuItem(R.drawable.img2, "삼겹 양념 덮밥", "3500", 3),
        MenuItem(R.drawable.img3, "직화 구이 덮밥", "3500", 20),
        MenuItem(R.drawable.img3, "참치 마요 덮밥", "3500", 2)
    )

    val PopoItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "420 쌀국수", "4200", 15),
        MenuItem(R.drawable.img2, "차돌 쌀국수", "5500", 11),
        MenuItem(R.drawable.img1, "마라 쌀국수", "6200", 3),
        MenuItem(R.drawable.img3, "직화구이 쌀국수", "6000", 13),
        MenuItem(R.drawable.img1, "완탕 쌀국수", "6000", 14),
    )

    val DonggasItems = mutableStateListOf(
        MenuItem(R.drawable.img1, "120 돈가스", "6300", 5),
        MenuItem(R.drawable.img1, "170 돈가스", "6300", 10),
        MenuItem(R.drawable.img2, "칼빔면", "4500", 19),
        MenuItem(R.drawable.img3, "카레라이스", "5200", 17),
        MenuItem(R.drawable.img3, "돈비세트", "7800", 2),
        MenuItem(R.drawable.img3, "돈카세트", "8500", 1),
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




}
