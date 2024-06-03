package com.example.teamproject.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R
import kotlinx.coroutines.launch

class StudentUnionMenuViewModel : ViewModel() {
    val BabItems = mutableStateListOf(
        MenuItem(R.drawable.saltbab, "소금 삼겹 덮밥", "3500", 1,"Bab", 0),
        MenuItem(R.drawable.redbab, "삼겹 양념 덮밥", "3500", 1,"Bab", 1),
        MenuItem(R.drawable.firebab, "직화 구이 덮밥", "3500", 1, "Bab", 2),
        MenuItem(R.drawable.tunabab, "참치 마요 덮밥", "3500", 1,"Bab", 3)
    )

    val PopoItems = mutableStateListOf(
        MenuItem(R.drawable.basicpopo, "420 쌀국수", "4200", 1,"Popo", 0),
        MenuItem(R.drawable.chadolpopo, "차돌 쌀국수", "5500", 1,"Popo", 1),
        MenuItem(R.drawable.marapopo, "마라 쌀국수", "6200", 1,"Popo", 2),
        MenuItem(R.drawable.firepopo, "직화구이 쌀국수", "6000", 1,"Popo", 3),
        MenuItem(R.drawable.boonjja, "분짜", "6300", 1,"Popo", 4),
    )

    val DonggasItems = mutableStateListOf(
        MenuItem(R.drawable.donggas, "왕돈가스", "6300", 1,"Donggas", 0),
        MenuItem(R.drawable.calbim, "칼빔면", "4500", 1,"Donggas", 1),
        MenuItem(R.drawable.curry, "카레라이스", "5200", 1,"Donggas", 2),
        MenuItem(R.drawable.donbi, "돈비세트", "7800", 1,"Donggas", 3),
        MenuItem(R.drawable.donka, "돈카세트", "8500", 1,"Donggas", 4),
    )

    val GookbabItems = mutableStateListOf(
        MenuItem(R.drawable.mool, "물냉면", "6000", 1,"Gookbab", 0),
        MenuItem(R.drawable.bibim, "비빔냉면", "6000", 1,"Gookbab", 1),
        MenuItem(R.drawable.meatgook, "수육국밥", "5900", 1,"Gookbab", 2),
        MenuItem(R.drawable.spicygook, "얼큰국밥", "6300", 1,"Gookbab", 3),
        MenuItem(R.drawable.mildgook, "맑은 순댓국밥", "6200", 1,"Gookbab", 4)
    )

    val BoonsikItems = mutableStateListOf(
        MenuItem(R.drawable.ramyeon, "황소라면", "3500", 1, "Boonsik",0),
        MenuItem(R.drawable.tteokbokki, "건대떡볶이", "3500", 1,"Boonsik",1),
        MenuItem(R.drawable.rabokki, "와우라볶이", "5500", 1,"Boonsik",2),
        MenuItem(R.drawable.soondae, "성적순대", "4000", 1,"Boonsik",3),
        MenuItem(R.drawable.twigim, "폭풍튀김", "4000", 1,"Boonsik",4),
        MenuItem(R.drawable.joomukrice, "장학금주먹밥", "2500", 1,"Boonsik",5),
        MenuItem(R.drawable.nalchibab, "날치알주먹밥", "3000", 1,"Boonsik",6),
        MenuItem(R.drawable.ramyeonbab, "라면+주먹밥", "5500", 1,"Boonsik",7),
        MenuItem(R.drawable.tteoksoon, "떡순이", "4500", 1,"Boonsik",8),
        MenuItem(R.drawable.tteoktwigim, "떡튀기", "4500", 1,"Boonsik",9),
        MenuItem(R.drawable.tteoktwisoon, "떡튀순", "7000", 1,"Boonsik",10)
    )

    val MaraItems = mutableStateListOf(
        MenuItem(R.drawable.maratang, "마라탕 기본", "5900", 1,"Mara",0),
        MenuItem(R.drawable.sogogimaratang, "소고기마라탕", "7400", 1,"Mara",1),
        MenuItem(R.drawable.boodaemaratang, "부대마라탕", "7800", 1,"Mara",2),
        MenuItem(R.drawable.mongttangmaratang, "몽땅마라탕", "8800", 1,"Mara",3),
        MenuItem(R.drawable.gguabarow, "미니꼬바로우", "3800", 1,"Mara",4),
        MenuItem(R.drawable.mandoo, "만두튀김", "1800", 1,"Mara",5),
        MenuItem(R.drawable.jjajang, "K 짜장", "4500", 1,"Mara",6),
        MenuItem(R.drawable.marajjajang, "마라 짜장", "5900", 1,"Mara",7)
    )

    val FirstfloorItems = mutableStateListOf(
        MenuItem(R.drawable.jaeyook, "고추장제육볶음", "5900", 20,"Firstfloor",0),
        MenuItem(R.drawable.currydonggas, "커리돈까스", "5500", 16,"Firstfloor",1),
        MenuItem(R.drawable.baekban, "KU백반", "5300", 15,"Firstfloor",2),
        MenuItem(R.drawable.soondoboo, "순두부라면", "3900", 11,"Firstfloor",3),
        MenuItem(R.drawable.woodong, "새우튀김우동", "4800", 13,"Firstfloor",4)
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
                "Firstfloor" -> {
                    if (FirstfloorItems[index].quantity >= quantity){
                        FirstfloorItems[index].quantity -= quantity
                    }
                }
            }
        }
    }


}