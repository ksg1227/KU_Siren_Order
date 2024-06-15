package com.example.teamproject.Screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavHostController
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes
import kr.co.bootpay.android.Bootpay
import kr.co.bootpay.android.events.BootpayEventListener
import kr.co.bootpay.android.models.BootExtra
import kr.co.bootpay.android.models.BootItem
import kr.co.bootpay.android.models.BootUser
import kr.co.bootpay.android.models.Payload

// 주문화면 or 장바구니에서 '결제하기' 버튼 누르면 PaymentScreen으로 이동
// PaymentScreen에서는 화면 시작할 때 PaymentTest() 함수 자동 호출 => 결제 창으로 이동함

@Composable
fun PaymentScreen(
    navController: NavHostController,
    fragmentManager: FragmentManager,
    payStoreName: String, // 결제 지점
    payPrice: Double, // 결제 금액
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        PaymentTest(
            fragmentManager = fragmentManager,
            context = context,
            payStoreName = payStoreName,
            payPrice = payPrice
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        GoBackToMain(navController)
    }
}

// KG이니시스 test
fun getBootUser(): BootUser? {
    val userId = "123411aaaaaaaaaaaabd4ss121"
    val user = BootUser()
    user.id = userId
    user.area = "서울"
    user.gender = 1 //1: 남자, 0: 여자
    user.email = "test1234@gmail.com"
    user.phone = "010-1234-4567"
    user.birth = "1988-06-10"
    user.username = "홍길동"
    return user
}

fun PaymentTest(
    fragmentManager: FragmentManager,
    context: Context,
    payStoreName: String, // 결제 지점
    payPrice: Double, // 결제 금액
) {
    Log.d("PaymentScreenTest", "PaymentTest 함수 호출됨") // 추가된 로그

//    var applicationId = "5b8f6a4d396fa665fdc2b5e8" // 테스트용 application id 가져옴
    var applicationId = "666aa034508d562d4b42ec83" // 우리 앱 application id (부트페이 관리자에서 실결제 막아둔 상태)

    val extra = BootExtra()
        .setCardQuota("0,2,3") // 일시불, 2개월, 3개월 할부 허용, 할부는 최대 12개월까지 사용됨 (5만원 이상 구매시 할부허용 범위)
    val items: MutableList<BootItem> = ArrayList()
    val item1 =
        BootItem().setName(payStoreName).setId("ITEM_CODE_MOUSE").setQty(1).setPrice(payPrice)
//    val item2 = BootItem().setName("키보드").setId("ITEM_KEYBOARD_MOUSE").setQty(1).setPrice(500.0)
    items.add(item1)
//    items.add(item2)
    val payload = Payload()

    payload.setApplicationId(applicationId)
        .setOrderName(payStoreName)
//            .setPg("페이레터")
//            .setMethod("카드자동")
        .setOrderId("1234")
        .setPrice(payPrice)
        .setUser(getBootUser())
        .setExtra(extra).items = items

    val map: MutableMap<String, Any> = HashMap()
    map["1"] = "abcdef"
    map["2"] = "abcdef55"
    map["3"] = 1234
    payload.metadata = map
    //        payload.setMetadata(new Gson().toJson(map));
    Bootpay.init(fragmentManager, context)
        .setPayload(payload)
        .setEventListener(object : BootpayEventListener {
            override fun onCancel(data: String) {
                Log.d("PaymentScreenTest", "cancel: $data")
            }

            override fun onError(data: String) {
                Log.d("PaymentScreenTest", "error: $data")
            }

            override fun onClose() {
                Bootpay.removePaymentWindow()
            }

            override fun onIssued(data: String) {
                Log.d("PaymentScreenTest", "issued: $data")
            }

            override fun onConfirm(data: String): Boolean {
                Log.d("PaymentScreenTest", "confirm: $data")
                //                        Bootpay.transactionConfirm(data); //재고가 있어서 결제를 진행하려 할때 true (방법 1)
                return true //재고가 있어서 결제를 진행하려 할때 true (방법 2)
                //                        return false; //결제를 진행하지 않을때 false
            }

            override fun onDone(data: String) {
                Log.d("PaymentScreenTest", "done: $data")
            }
        }).requestPayment()

}

// '메인화면으로 이동하기' btn
@Composable
fun GoBackToMain(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .width(340.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                Log.d("PaymentScreenTest", "메인화면으로 돌아가기 버튼 클릭됨")
                navController.navigate(Routes.Start.route)
            }
            .background(colorResource(id = R.color.green_65a25b)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "메인화면으로 돌아가기",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
    }
}

//@Composable
//fun PaymentScreen(
//    navController: NavHostController,
//    fragmentManager: FragmentManager,
//    modifier: Modifier = Modifier
//) {
//
//    var applicationId = "5b8f6a4d396fa665fdc2b5e8" // 테스트용 application id 가져옴
//    val context = LocalContext.current
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        TopAppBar(
//            onBackIconClick = { navController.popBackStack() },
//            title = "결제 수단 선택",
//            titleColor = Color.Black,
//            onRightIconClick = { },
//            rightIconImgId = null
//        )
//        Spacer(Modifier.weight(1f))
//        Text(
//            text = "결제수단을 선택해주세요",
//            fontSize = 16.sp,
//            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
//        )
//        Spacer(modifier = Modifier.height(30.dp))
//        Row {
//            PaymentBtnWithText(
//                text = "신용・체크카드",
//                onClick = {
//                    Log.d("PaymentScreenn", "신용체크카드 클릭됨")
//                    PaymentTest(applicationId, fragmentManager, context)
//                }
//            )
//            Spacer(modifier = Modifier.width(20.dp))
//            PaymentAppButton(
//                // toss 앱 연결
//                imgId = R.drawable.img_tosspay,
//                packageName = "viva.republica.toss"
//            )
//        }
//        Spacer(modifier = Modifier.height(20.dp))
//        Row {
//            PaymentAppButton(
//                // kakaopay 앱 연결
//                imgId = R.drawable.img_kakaopay,
//                packageName = "com.kakaopay.app"
//            )
//            Spacer(modifier = Modifier.width(20.dp))
//            PaymentAppButton(
//                // payco 앱 연결
//                imgId = R.drawable.img_payco,
//                packageName = "com.nhnent.payapp"
//            )
//        }
//        Spacer(Modifier.weight(1f))
//        GoPaymentBtn {
//        }
//        Spacer(modifier = Modifier.height(30.dp))
//    }
//}
//
//// 각 결제 수단을 위한 앱 버튼
//@Composable
//fun PaymentAppButton(
//    imgId: Int,
//    packageName: String
//) {
//    val context = LocalContext.current
//    val intent = context.packageManager.getLaunchIntentForPackage(packageName)
//
//    PaymentBtnWithImg(imgId = imgId) {
//        if (intent != null) {
//            context.startActivity(intent)
//        } else {
//            // 앱이 설치되어 있지 않다면 플레이스토어로 이동
//            val uri = Uri.parse("market://details?id=$packageName")
//            val marketIntent = Intent(Intent.ACTION_VIEW, uri)
//            context.startActivity(marketIntent)
//        }
//    }
//}
//
//// 결제 수단 img btn
//@Composable
//fun PaymentBtnWithImg(
//    imgId: Int,
//    onClick: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .width(160.dp)
//            .height(80.dp)
//            .clip(RoundedCornerShape(10.dp))
//            .clickable(onClick = onClick)
//            .border(1.dp, colorResource(id = R.color.gray_b3b3b3), RoundedCornerShape(10.dp)),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = imgId),
//            contentDescription = "Payment Button",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.height(22.dp)
//        )
//    }
//}
//
//// 결제 수단 text btn
//@Composable
//fun PaymentBtnWithText(
//    text: String,
//    onClick: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .width(160.dp)
//            .height(80.dp)
//            .clip(RoundedCornerShape(10.dp))
//            .clickable(onClick = onClick)
//            .border(1.dp, colorResource(id = R.color.gray_b3b3b3), RoundedCornerShape(10.dp)),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = text,
//            fontSize = 16.sp,
//            fontFamily = FontFamily(Font(R.font.pretendard_medium))
//        )
//    }
//}
//
//// '결제하기' btn
//@Composable
//fun GoPaymentBtn(
//    onClick: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .width(340.dp)
//            .height(40.dp)
//            .clip(RoundedCornerShape(10.dp))
//            .clickable(onClick = onClick)
//            .background(colorResource(id = R.color.green_65a25b)),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "결제하기",
//            color = Color.White,
//            fontSize = 16.sp,
//            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
//        )
//    }
//}
//
//
//// KG이니시스 test
//fun getBootUser(): BootUser? {
//    val userId = "123411aaaaaaaaaaaabd4ss121"
//    val user = BootUser()
//    user.id = userId
//    user.area = "서울"
//    user.gender = 1 //1: 남자, 0: 여자
//    user.email = "test1234@gmail.com"
//    user.phone = "010-1234-4567"
//    user.birth = "1988-06-10"
//    user.username = "홍길동"
//    return user
//}
//
//fun PaymentTest(applicationId: String, fragmentManager: FragmentManager, context: Context) {
//    Log.d("PaymentScreennTest", "PaymentTest 함수 호출됨") // 추가된 로그
//
//    val extra = BootExtra()
//        .setCardQuota("0,2,3") // 일시불, 2개월, 3개월 할부 허용, 할부는 최대 12개월까지 사용됨 (5만원 이상 구매시 할부허용 범위)
//    val items: MutableList<BootItem> = ArrayList()
//    val item1 = BootItem().setName("마우's 스").setId("ITEM_CODE_MOUSE").setQty(1).setPrice(500.0)
//    val item2 = BootItem().setName("키보드").setId("ITEM_KEYBOARD_MOUSE").setQty(1).setPrice(500.0)
//    items.add(item1)
//    items.add(item2)
//    val payload = Payload()
//
//    payload.setApplicationId(applicationId)
//        .setOrderName("사이렌오더 결제테스트")
////            .setPg("페이레터")
////            .setMethod("카드자동")
//        .setOrderId("1234")
//        .setPrice(1000.0)
//        .setUser(getBootUser())
//        .setExtra(extra).items = items
//
//    val map: MutableMap<String, Any> = HashMap()
//    map["1"] = "abcdef"
//    map["2"] = "abcdef55"
//    map["3"] = 1234
//    payload.metadata = map
//    //        payload.setMetadata(new Gson().toJson(map));
//    Bootpay.init(fragmentManager, context)
//        .setPayload(payload)
//        .setEventListener(object : BootpayEventListener {
//            override fun onCancel(data: String) {
//                Log.d("bootpay", "cancel: $data")
//            }
//
//            override fun onError(data: String) {
//                Log.d("bootpay", "error: $data")
//            }
//
//            override fun onClose() {
//                Bootpay.removePaymentWindow()
//            }
//
//            override fun onIssued(data: String) {
//                Log.d("bootpay", "issued: $data")
//            }
//
//            override fun onConfirm(data: String): Boolean {
//                Log.d("bootpay", "confirm: $data")
//                //                        Bootpay.transactionConfirm(data); //재고가 있어서 결제를 진행하려 할때 true (방법 1)
//                return true //재고가 있어서 결제를 진행하려 할때 true (방법 2)
//                //                        return false; //결제를 진행하지 않을때 false
//            }
//
//            override fun onDone(data: String) {
//                Log.d("done", data)
//            }
//        }).requestPayment()
//
//}