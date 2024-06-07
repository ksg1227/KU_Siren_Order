package com.example.teamproject.Item

data class CartMenuItem(
    val menuItem: MenuItem,
    val size: String?,
    var optionList: List<String>?
)
