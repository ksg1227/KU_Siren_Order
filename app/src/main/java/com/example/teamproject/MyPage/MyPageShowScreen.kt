package com.example.teamproject.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teamproject.R
import com.example.teamproject.Screen.TopAppBar
import com.example.teamproject.navigation.Routes


@Composable
fun MyPageShowScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // 상단
            TopAppBar(
                onBackIconClick = { navController.popBackStack() },
                title = "개인 정보 확인",
                titleColor = Color.Black,
                onRightIconClick = { /*TODO*/ },
                rightIconImgId = null
            )

            // 내용
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = R.drawable.konkuk),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(60.dp))
                UserInfoRow(label = "아이디", value = "None") /* todo: 개인정보 연결 */
                UserInfoRow(label = "이름", value = "None")
                UserInfoRow(label = "학번/학과", value = "None")
                UserInfoRow(label = "이메일", value = "None")
                UserInfoRow(label = "휴대폰 번호", value = "None")
            }
        }
    }
}

@Composable
fun UserInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(2f)
        )
    }
}
