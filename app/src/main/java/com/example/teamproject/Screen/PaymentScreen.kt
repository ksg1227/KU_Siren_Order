package com.example.teamproject.Screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes

@Composable
fun PaymentScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            text = "결제수단을 선택해주세요",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            PaymentBtnWithText(text = "신용・체크카드") {}
            Spacer(modifier = Modifier.width(20.dp))
            PaymentBtnWithImg(imgId = R.drawable.img_tosspay) {}
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            PaymentBtnWithImg(imgId = R.drawable.img_kakaopay) {}
            Spacer(modifier = Modifier.width(20.dp))
            PaymentBtnWithImg(imgId = R.drawable.img_payco) {}
        }
        Spacer(Modifier.weight(1f))
        GoPaymentBtn {
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

// 결제 수단 img btn
@Composable
fun PaymentBtnWithImg(
    imgId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .border(1.dp, colorResource(id = R.color.gray_b3b3b3), RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imgId),
            contentDescription = "Payment Button",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(22.dp)
        )
    }
}

// 결제 수단 text btn
@Composable
fun PaymentBtnWithText(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .border(1.dp, colorResource(id = R.color.gray_b3b3b3), RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_medium))
        )
    }
}

// '결제하기' btn
@Composable
fun GoPaymentBtn(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(340.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .background(colorResource(id = R.color.green_65a25b)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "결제하기",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
    }
}