package com.example.teamproject.Restio

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.teamproject.Item.MenuItem
import com.example.teamproject.R

class AnimalLifeRestioMenuViewModel : ViewModel() {

    val productsHot = mutableStateListOf(
        MenuItem(R.drawable.img_hot_americano, "아메리카노", "2,600원", 999, "커피 HOT", 1),
        MenuItem(R.drawable.img_hot_cafelatte, "카페라떼", "2,800원", 999, "커피 HOT", 2),
        MenuItem(R.drawable.img_hot_cappuccino, "카푸치노", "2,800원", 999, "커피 HOT", 3),
        MenuItem(R.drawable.yeonu, "건국연유라떼", "3,000원", 999, "커피 HOT", 4),
        MenuItem(R.drawable.img_hot_caramel, "캬라멜마끼아또", "3,200원", 999, "커피 HOT", 5),
        MenuItem(R.drawable.img_hot_hazelnut, "헤이즐넛라떼", "3,000원", 999, "커피 HOT", 6),
        MenuItem(R.drawable.img_hot_vanilla, "바닐라라떼", "3,000원", 999, "커피 HOT", 7),
        MenuItem(R.drawable.img_hot_cafemocha, "카페모카", "3,200원", 999, "커피 HOT", 8)
    )

    // 커피 ICE
    val productsIce = mutableStateListOf(
        MenuItem(R.drawable.yeonu, "아이스건국닥터유라떼", "3,200원", 999, "커피 ICE", 0),
        MenuItem(R.drawable.img_ice_americano, "아이스 아메리카노", "3,000원", 999, "커피 ICE", 1),
        MenuItem(R.drawable.img_ice_cafelatte, "아이스 카페라떼", "3,200원", 999, "커피 ICE", 2),
        MenuItem(R.drawable.yeonu, "아이스 건국연유라떼", "3,400원", 999, "커피 HOT", 4),
        MenuItem(R.drawable.img_ice_cappuccino, "아이스 카푸치노", "3,200원", 999, "커피 ICE", 3),
        MenuItem(R.drawable.img_ice_caramel, "아이스 카라멜 마끼아또", "3,500원", 999, "커피 ICE", 4),
        MenuItem(R.drawable.img_ice_vanilla, "아이스 바닐라라떼", "3,200원", 999, "커피 ICE", 5),
        MenuItem(R.drawable.img_ice_cafemocha, "아이스 카페모카", "3,500원", 999, "커피 ICE", 6)
    )

    // 티라떼/아이스티
    val productsTea = mutableStateListOf(
        MenuItem(R.drawable.img_hotchoco, "핫초코", "2,600원", 999, "티라떼|아이스티", 0),
        MenuItem(R.drawable.img_chocolatte, "아이스초코", "2,900원", 999, "티라떼|아이스티", 1),
        MenuItem(R.drawable.img_greentealatte, "그린티라떼", "3,000원", 999, "티라떼|아이스티", 2),
        MenuItem(R.drawable.img_greentea, "아이스그린티라떼", "3,400원", 999, "티라떼|아이스티", 3),
        MenuItem(R.drawable.img_milktea, "밀크티", "3,000원", 999, "티라떼|아이스티", 4),
        MenuItem(R.drawable.img_milktea, "아이스밀크티", "3,400원", 999, "티라떼|아이스티", 5),
        MenuItem(R.drawable.img_strawberrylette, "딸기라떼", "3,500원", 999, "티라떼|아이스티", 6)
    )

    // 에이드/티백
    val productsAdeSmoothy = mutableStateListOf(
        MenuItem(R.drawable.img_lemonade, "레몬에이드", "2,800원", 999, "에이드|스무디", 0),
        MenuItem(R.drawable.img_greentea, "자몽에이드", "2,800원", 999, "에이드|스무디", 1),
        MenuItem(R.drawable.img_greengrapeade, "청포도에이드", "2,800원", 999, "에이드|스무디", 2),
        MenuItem(R.drawable.img_strawberryade, "딸기에이드", "2,800원", 999, "에이드|스무디", 3),
        MenuItem(R.drawable.img_fashionfruitsade, "망고|패션후르츠에이드", "2,800원", 999, "에이드|스무디", 4),
        MenuItem(R.drawable.img_plainyogurtsmoothy, "플레인요거트스무디", "3,600원", 999, "에이드|스무디", 5),
        MenuItem(R.drawable.img_smoothie_strawberry_yogurt, "딸기요거트스무디", "3,600원", 999, "에이드|스무디", 6),
        MenuItem(R.drawable.img_smoothie_bluberry_yogurt, "블루베리요거트스무디", "3,600원", 999, "에이드|스무디", 7)
    )

    // 레스치노/스무디/과일주스
    val productsZ = mutableStateListOf(
        MenuItem(R.drawable.img_peachicetea, "복숭아아이스티", "2,800원", 999, "티", 0),
        MenuItem(R.drawable.img_jamonhoneyblacktea, "자몽허니블랙티", "3,400원", 999, "티", 1),
        MenuItem(R.drawable.img_greentea, "녹차", "2,600원", 999, "티", 2),
        MenuItem(R.drawable.img_rgraytea, "얼그레이", "2,600원", 999, "티", 3),
        MenuItem(R.drawable.img_camomiletea, "캐모마일", "2,600원", 999, "티", 4),
        MenuItem(R.drawable.img_peperminttea, "페퍼민트", "2,600원", 999, "티", 5),
        MenuItem(R.drawable.img_lemontea, "레몬차", "2,600원", 999, "티", 6),
        MenuItem(R.drawable.img_ujatea, "유자차", "2,600원", 999, "티", 7)
    )

    // 주스|병음료
    val productsJuice = mutableStateListOf(
        MenuItem(R.drawable.img_strawberryjuice, "딸기주스", "3,400원", 999, "주스|병음료", 0),
        MenuItem(R.drawable.img_bananajuice, "바나나주스", "3,400원", 999, "주스|병음료", 1),
        MenuItem(R.drawable.img_strawberrybananajuice, "딸기바나나주스", "3,400원", 999, "주스|병음료", 2),
        MenuItem(R.drawable.img_sparklingapplejuice, "스파클링애플주스", "2,800원", 999, "주스|병음료", 3),
        MenuItem(R.drawable.img_applejuice, "애플주스", "2,800원", 999, "주스|병음료", 4),
        MenuItem(R.drawable.img_evian, "에비앙", "2,000원", 999, "주스|병음료", 5)
    )


    val productsMilk = mutableStateListOf(
        MenuItem(R.drawable.img_blackcongduyu, "국산콩두유160ml", "1,000원", 999, "오늘의 우유", 0),
        MenuItem(R.drawable.img_chocomilk, "초코우유", "500원", 999, "오늘의 우유", 1),
        MenuItem(R.drawable.img_strawberrymilk, "딸기우유", "500원", 999, "오늘의 우유", 2),
        MenuItem(R.drawable.img_konkukmilk, "건국우유200ml(오늘의 우유)", "500원", 999, "오늘의 우유", 3)
    )

    val productsSandwich = mutableStateListOf(
        MenuItem(R.drawable.img_chiabatasandwitch, "치아바타 샌드위치", "3,100원", 999, "샌드위치", 0),
        MenuItem(R.drawable.img_hotdog_original, "오리지날핫도그", "3,100원", 999, "샌드위치", 1),
        MenuItem(R.drawable.img_hotdog_chili, "칠리핫도그", "3,100원", 999, "샌드위치", 2),
        MenuItem(R.drawable.img_sandwitch_chiabata, "햄치즈샌드위치", "3,100원", 999, "샌드위치", 3),
        MenuItem(R.drawable.img_sandwitch_chiabata, "참치샌드위치", "3,100원", 999, "샌드위치", 4),
        MenuItem(R.drawable.img_saladbread, "어니언사라다빵", "3,100원", 999, "샌드위치", 5),
        MenuItem(R.drawable.img_saladbread, "포테이토사라다빵", "3,100원", 999, "샌드위치", 6),
        MenuItem(R.drawable.img_sandwitch, "버터크림햄치즈샌드위치", "3,100원", 999, "샌드위치", 7),
        MenuItem(R.drawable.img_sandwitch, "땅콩샌드위치", "3,100원", 999, "샌드위치", 8),
        MenuItem(R.drawable.img_sandwitch, "딸기샌드위치", "3,100원", 999, "샌드위치", 9)
    )

    val productsMap = mutableStateMapOf(
        "커피 HOT" to productsHot,
        "커피 ICE" to productsIce,
        "라떼류" to productsTea,
        "에이드|스무디" to productsAdeSmoothy,
        "티" to productsZ,
        "주스|병음료" to productsJuice,
        "오늘의 우유" to productsMilk,
        "샌드위치" to productsSandwich
    )
}