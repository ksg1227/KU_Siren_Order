package com.example.teamproject.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.teamproject.R
import com.example.teamproject.Screen.TopAppBar

@Composable
fun MyPageMainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                onBackIconClick = { /*TODO*/ },
                title = "마이페이지",
                titleColor = Color.Black,
                onRightIconClick = { /*TODO*/ },
                rightIconImgId = null
            )

            Spacer(modifier = Modifier.padding(top = 150.dp))
            Image(
                painter = painterResource(id = R.drawable.konkuk),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 50.dp)
            )
            Button(
                onClick = { /* Todo */ },
                modifier = Modifier
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp))
                    .width(330.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "개인 정보 확인")
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Button(
                onClick = { /* todo */ },
                modifier = Modifier
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp))
                    .width(330.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "개인 정보 수정")
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(
                modifier = Modifier
                    .width(350.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = "로그아웃", Modifier.clickable {  }, color = Color.Gray)
            }
        }
    }
}