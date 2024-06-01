package com.example.teamproject.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes

@Composable
fun CartScreen(navController : NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TopAppBar( // 상단바
            onBackIconClick = {
                              navController.popBackStack()
            },
            title = "장바구니",
            titleColor = Color.Black,
            onRightIconClick = { },
            rightIconImgId = null
        )
        Spacer(Modifier.weight(1f))
        GoOrderBtn {} // '주문하기' btn
        Spacer(modifier = Modifier.height(30.dp))
    }
}

// '주문하기' btn
@Composable
fun GoOrderBtn(
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
            text = "총 0원 주문하기",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
    }
}