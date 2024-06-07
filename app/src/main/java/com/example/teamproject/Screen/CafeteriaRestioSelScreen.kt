package com.example.teamproject.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import com.example.teamproject.R

@Composable
fun CafeteriaRestioSelScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // 상단 바 구현
            TopAppBar(
                onBackIconClick = { /*TODO*/ },
                title = "학식 / 레스티오",
                titleColor = Color.Black,
                onRightIconClick = { /*TODO*/ },
                rightIconImgId = null
            )

            // 내용 화면 구성
            Spacer(modifier = Modifier.height(85.dp))
            Image(
                painter = painterResource(id = R.drawable.konkuk),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "000 학우님 안녕하세요", /* todo 아이디 연결 */
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* TODO 화면 전환*/ },
                    modifier = Modifier
                        .height(250.dp)
                        .weight(1f)
                        .background(Color(0xFFF5E6CA), shape = RoundedCornerShape(16.dp))
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.eat),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .padding(bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(text = "학식", fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /* TODO 화면 전환 */ },
                    modifier = Modifier
                        .height(250.dp)
                        .weight(1f)
                        .background(Color(0xFFD6E8E8), shape = RoundedCornerShape(16.dp))
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.restio),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .padding(bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(text = "레스티오", fontSize = 20.sp)
                    }
                }

            }
        }
    }
}

