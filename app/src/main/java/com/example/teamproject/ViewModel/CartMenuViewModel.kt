package com.example.teamproject.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.teamproject.Item.MenuItem

class CartMenuViewModel : ViewModel() {

    val library_GusiaMenuList = mutableStateListOf<MenuItem>()

    val studentUnion_GusiaMenuList = mutableStateListOf<MenuItem>()

    val studentUnion_FirstfloorMenuList = mutableStateListOf<MenuItem>()
}