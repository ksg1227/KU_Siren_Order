package com.example.teamproject.Restio

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R

class AnimalLifeRestioMenuViewModel : ViewModel() {

    val productsHot = mutableStateListOf(
        MenuItem(R.drawable.img_hot_espresso, "에스프레소", "2,600원", 999, "커피 HOT", 0),
        MenuItem(R.drawable.img_hot_americano, "아메리카노", "2,600원", 999, "커피 HOT", 1),
        MenuItem(R.drawable.img_hot_cafelatte, "카페라떼", "2,800원", 999, "커피 HOT", 2),
        MenuItem(R.drawable.img_hot_cappuccino, "카푸치노", "2,800원", 999, "커피 HOT", 3),
        MenuItem(R.drawable.img_hot_caramel, "캬라멜마끼아또", "3,200원", 999, "커피 HOT", 4),
        MenuItem(R.drawable.img_hot_hazelnut, "헤이즐넛라떼", "3,000원", 999, "커피 HOT", 5),
        MenuItem(R.drawable.img_hot_vanilla, "바닐라라떼", "3,000원", 999, "커피 HOT", 6),
        MenuItem(R.drawable.img_hot_cafemocha, "카페모카", "3,200원", 999, "커피 HOT", 7),
        MenuItem(R.drawable.img_hot_ginger, "진저라떼", "3,000원", 999, "커피 HOT", 8)
    )

    // 커피 ICE
    val productsIce = mutableStateListOf(
        MenuItem(R.drawable.img_ice_americano, "아이스 아메리카노", "3,000원", 999, "커피 ICE", 0),
        MenuItem(R.drawable.img_ice_cafelatte, "아이스 카페라떼", "3,200원", 999, "커피 ICE", 1),
        MenuItem(R.drawable.img_ice_cappuccino, "아이스 카푸치노", "3,200원", 999, "커피 ICE", 2),
        MenuItem(R.drawable.img_ice_caramel, "아이스 카라멜 마끼아또", "3,500원", 999, "커피 ICE", 3),
        MenuItem(R.drawable.img_ice_vanilla, "아이스 바닐라라떼", "3,200원", 999, "커피 ICE", 4),
        MenuItem(R.drawable.img_ice_cafemocha, "아이스 카페모카", "3,500원", 999, "커피 ICE", 5)
    )

    // 티라떼/아이스티
    val productsTea = mutableStateListOf(
        MenuItem(R.drawable.img_tea_hot_choco, "초코라떼", "2,600원", 999, "티라떼|아이스티", 0),
        MenuItem(R.drawable.img_tea_hot_greentea, "그린티라떼", "3,000원", 999, "티라떼|아이스티", 1),
        MenuItem(R.drawable.img_tea_hot_blacktea, "홍차라떼", "3,000원", 999, "티라떼|아이스티", 2),
        MenuItem(R.drawable.img_tea_hot_sweetpotato, "고구마라떼", "3,000원", 999, "티라떼|아이스티", 3),
        MenuItem(R.drawable.img_tea_ice_peach, "복숭아아이스티", "2,800원", 999, "티라떼|아이스티", 4),
        MenuItem(R.drawable.img_tea_ice_choco, "아이스 초코라떼", "2,900원", 999, "티라떼|아이스티", 5),
        MenuItem(R.drawable.img_tea_ice_greentea, "아이스 그린티라떼", "3,400원", 999, "티라떼|아이스티", 6),
        MenuItem(R.drawable.img_tea_ice_blacktea, "아이스 홍차라떼", "3,400원", 999, "티라떼|아이스티", 7),
        MenuItem(R.drawable.img_tea_ice_sweetpotato, "아이스 고구마라떼", "3,400원", 999, "티라떼|아이스티", 8),
        MenuItem(R.drawable.img_tea_ice_grapefruit, "자몽차", "2,000원", 999, "티라떼|아이스티", 9),
        MenuItem(R.drawable.img_tea_ice_lemon, "레몬차", "2,000원", 999, "티라떼|아이스티", 10),
        MenuItem(R.drawable.img_tea_ice_citron, "유자차", "2,000원", 999, "티라떼|아이스티", 11)
    )

    // 에이드/티백
    val productsAdeTeabag = mutableStateListOf(
        MenuItem(R.drawable.img_adeteabag_chamomile, "카모마일", "2,600원", 999, "에이드|티백", 0),
        MenuItem(R.drawable.img_adeteabag_peppermint, "페퍼민트", "2,600원", 999, "에이드|티백", 1),
        MenuItem(R.drawable.img_adeteabag_greentea, "녹차", "2,600원", 999, "에이드|티백", 2),
        MenuItem(R.drawable.img_adeteabag_blacktea, "홍차", "2,600원", 999, "에이드|티백", 3),
        MenuItem(R.drawable.img_adeteabag_citronmint, "유자민트티", "2,900원", 999, "에이드|티백", 4),
        MenuItem(R.drawable.img_adeteabag_blueberry, "블루베리에이드", "2,800원", 999, "에이드|티백", 5),
        MenuItem(R.drawable.img_adeteabag_greengrape, "청포도에이드", "2,800원", 999, "에이드|티백", 6),
        MenuItem(R.drawable.img_adeteabag_grapefruit, "자몽에이드", "2,800원", 999, "에이드|티백", 7),
        MenuItem(R.drawable.img_adeteabag_lemon, "레몬에이드", "2,800원", 999, "에이드|티백", 8)
    )

    // 레스치노/스무디/과일주스
    val productsZ = mutableStateListOf(
        MenuItem(R.drawable.img_smoothie_strawberry, "딸기", "3,400원", 999, "레스치노|스무디|과일주스", 0),
        MenuItem(R.drawable.img_smoothie_bluberry_yogurt, "블루베리", "3,400원", 999, "레스치노|스무디|과일주스", 1),
        MenuItem(R.drawable.img_smoothie_mango, "망고", "3,400원", 999, "레스치노|스무디|과일주스", 2),
        MenuItem(R.drawable.img_smoothie_plainyogurt, "플레인요거트", "3,600원", 999, "레스치노|스무디|과일주스", 3),
        MenuItem(R.drawable.img_smoothie_strawberry_yogurt, "딸기요거트", "3,600원", 999, "레스치노|스무디|과일주스", 4),
        MenuItem(R.drawable.img_smoothie_blueberry_yogurt, "블루베리요거트", "3,600원", 999, "레스치노|스무디|과일주스", 5),
        MenuItem(R.drawable.img_smoothie_lemon_yogurt, "레몬요거트", "3,600원", 999, "레스치노|스무디|과일주스", 6)
    )

    // 샌드위치/핫도그
    val productsSandHot = mutableStateListOf(
        MenuItem(R.drawable.img_sandwitch_chiabata, "레스티오치아바타", "3,100원", 999, "샌드위치|핫도그", 0),
        MenuItem(R.drawable.img_sandwitch_kaya, "카야샌드위치", "2,200원", 999, "샌드위치|핫도그", 1),
        MenuItem(R.drawable.img_sandwitch_hamchees, "햄치즈샌드위치", "2,600원", 999, "샌드위치|핫도그", 2),
        MenuItem(R.drawable.img_hotdog_original, "오리지날핫도그", "3,200원", 999, "샌드위치|핫도그", 3),
        MenuItem(R.drawable.img_hotdog_chili, "칠리핫도그", "3,200원", 999, "샌드위치|핫도그", 4)
    )


    // 베이커리
    val bakery = mutableStateListOf(
        MenuItem(R.drawable.img_cookie_brownie, "브라우니쿠키", "3,200원", 999, "베이커리", 0),
        MenuItem(R.drawable.img_cookie_checker, "체커쿠키", "3,200원", 999, "베이커리", 1),
        MenuItem(R.drawable.img_cookie_choco, "초코칩쿠키", "3,200원", 999, "베이커리", 2),
        MenuItem(R.drawable.img_muffine_choco, "초코칩머핀", "2,200원", 999, "베이커리", 3),
        MenuItem(R.drawable.img_muffine_cheese, "치즈머핀", "2,200원", 999, "베이커리", 4),
        MenuItem(R.drawable.img_muffine_bluberry, "블루베리머핀", "2,200원", 999, "베이커리", 5),
        MenuItem(R.drawable.img_waffle_plain, "플레인와플", "2,200원", 999, "베이커리", 6)
    )

    val productsMap = mutableStateMapOf(
        "커피 HOT" to productsHot,
        "커피 ICE" to productsIce,
        "티라떼|아이스티" to productsTea,
        "에이드|티백" to productsAdeTeabag,
        "레스치노|스무디|과일주스" to productsZ,
        "베이커리" to bakery,
        "샌드위치|핫도그" to productsSandHot
    )

}