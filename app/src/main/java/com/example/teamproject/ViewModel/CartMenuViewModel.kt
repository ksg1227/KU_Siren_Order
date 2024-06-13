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

    val engineeringRestioMenuList = mutableStateListOf<CartMenuItem>()
    val selected_engineeringRestioMenuList = mutableStateListOf<CartMenuItem>()

    val animalLifeRestioMenuList = mutableStateListOf<CartMenuItem>()
    val selected_animalLifeRestioMenuList = mutableStateListOf<CartMenuItem>()

    val managementRestioMenuList = mutableStateListOf<CartMenuItem>()
    val selected_managementRestioMenuList = mutableStateListOf<CartMenuItem>()

    val industryRestioMenuList = mutableStateListOf<CartMenuItem>()
    val selected_industryRestioMenuList = mutableStateListOf<CartMenuItem>()

    val libraryRestioMenuList = mutableStateListOf<CartMenuItem>()
    val selected_libraryRestioMenuList = mutableStateListOf<CartMenuItem>()

    fun calculateTotalPrice(placeName: String): Int {
        val totalPrice = when (placeName) {
            "학생회관 1층 학식" -> studentUnion_FirstfloorMenuList.sumOf { it.menuItem.price.toInt()}
            "학생회관 지하 학식(구시아푸드)" -> studentUnion_GusiaMenuList.sumOf { it.menuItem.price.toInt()}
            "상허기념도서관 지하 학식(구시아푸드)" -> library_GusiaMenuList.sumOf { it.menuItem.price.toInt()}
            "공학관 레스티오" -> engineeringRestioMenuList.sumOf { it.menuItem.price.toInt() }
            "동물생명과학관 레스티오" -> animalLifeRestioMenuList.sumOf { it.menuItem.price.toInt() }
            "상허기념도서관 레스티오" -> libraryRestioMenuList.sumOf { it.menuItem.price.toInt() }
            "경영관 레스티오" -> managementRestioMenuList.sumOf { it.menuItem.price.toInt() }
            "산학협동관 레스티오" -> industryRestioMenuList.sumOf { it.menuItem.price.toInt() }
            else -> 0
        }
        return totalPrice
    }


}