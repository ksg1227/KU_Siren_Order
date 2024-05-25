package com.example.teamproject.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes

@Composable
fun StartScreen(navController: NavHostController) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.konkuk),
            contentDescription = "건국대로고",
            modifier = Modifier.padding(bottom = 60.dp)
        )
        
        Text(text = "KU 레스티오/학식 사이렌 오더",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 40.dp))

        Button(
            onClick = { navController.navigate(Routes.Login.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // 배경색
                contentColor = Color.Black // 텍스트 색상
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .width(300.dp)
                .shadow(2.dp, RoundedCornerShape(20.dp))
        ) {
            Text(text = "로그인")
        }

        Button(
            onClick = { navController.navigate(Routes.LibraryGusia.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // 배경색
                contentColor = Color.Black // 텍스트 색상
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .width(300.dp)
                .shadow(2.dp, RoundedCornerShape(20.dp))
        ) {
            Text(text = "회원가입")
        }
        Button(
            onClick = { navController.navigate(Routes.Map.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // 배경색
                contentColor = Color.Black // 텍스트 색상
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .width(300.dp)
                .shadow(2.dp, RoundedCornerShape(20.dp))
        ) {
            Text(text = "맵 보기!")
        }
    }
}
