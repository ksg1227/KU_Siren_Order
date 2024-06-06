package com.example.teamproject.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.teamproject.Item.CartMenuItem
import com.example.teamproject.Item.MenuItem

class CartMenuViewModel : ViewModel() {

    val library_GusiaMenuList = mutableStateListOf<CartMenuItem>()
    val selected_library_GusiaMenuList = mutableStateListOf<CartMenuItem>()

    val studentUnion_GusiaMenuList = mutableStateListOf<CartMenuItem>()
    val selected_studentUnion_GusiaMenuList = mutableStateListOf<CartMenuItem>()

    val studentUnion_FirstfloorMenuList = mutableStateListOf<CartMenuItem>()
    val selected_studentUnion_FirstfloorMenuList = mutableStateListOf<CartMenuItem>()

    fun calculateTotalPrice(placeName: String): Int {
        val totalPrice = when (placeName) {
            "학생회관 1층 학식" -> studentUnion_FirstfloorMenuList.sumOf { it.menuItem.price.toInt()}
            "학생회관 지하 학식(구시아푸드)" -> studentUnion_GusiaMenuList.sumOf { it.menuItem.price.toInt()}
            "상허기념도서관 지하 학식(구시아푸드)" -> library_GusiaMenuList.sumOf { it.menuItem.price.toInt()}
            else -> 0
        }

        return totalPrice
    }


}