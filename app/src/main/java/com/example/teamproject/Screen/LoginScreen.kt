package com.example.teamproject.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "로그인",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        
        Spacer(modifier = Modifier.padding(bottom = 150.dp))
        
        Image(
            painter = painterResource(id = R.drawable.konkuk),
            contentDescription = "건국대로고",
            modifier = Modifier
                .padding(bottom = 60.dp)
                .size(100.dp)
        )

        Text(
            text = "KU 레스티오/학식 사이렌 오더",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
            color = Color(0xFF000000)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "아이디",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                color = Color(0xFF444444)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = {
                    Text(
                        "아이디를 입력하세요.",
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color(0xFFB3B3B3)
                    )
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "아이디 아이콘"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),

                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "비밀번호",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                color = Color(0xFF444444)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        "비밀번호를 입력하세요.",
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = Color(0xFFB3B3B3)
                    )
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "비밀번호 아이콘"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(130.dp))

        Button(
            onClick = {
                if (username.isEmpty() || password.isEmpty()) {
                    errorMessage = "아이디와 패스워드를 모두 입력해주세요."
                } else {
                    errorMessage = ""
                    // 로그인 로직을 추가하세요
                    navController.navigate(Routes.StudentUnionGusia.route)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF65A25B), // 배경색
                contentColor = Color.White // 텍스트 색상
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "로그인",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

