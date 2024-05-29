package com.example.teamproject.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes


@Composable
fun SignUpScreen(navController: NavHostController) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var emailUser by remember { mutableStateOf("") }
    var emailDomain by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var showEmptyFieldsError by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
    ) {
        Text(
            text = "회원가입",
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 40.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )

        Text(
            text = "아이디 *",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        OutlinedTextField(
            value = id,
            onValueChange = {
                id = it
                showEmptyFieldsError = false
            },
            label = { Text("아이디를 입력하세요.",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color(0xFFB3B3B3)
            ) },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true
        )

        Text(
            text = "비밀번호 *",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                showError = confirmPassword != it && confirmPassword.isNotEmpty()
                showEmptyFieldsError = false
            },
            label = { Text("비밀번호를 입력하세요.",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color(0xFFB3B3B3)) },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Text(
            text = "비밀번호 확인 *",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                showError = password != it && it.isNotEmpty()
                showEmptyFieldsError = false
            },
            label = { Text("비밀번호를 한 번 더 입력하세요.",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color(0xFFB3B3B3)) },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            isError = showError,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
        if (showError) {
            Text(
                text = "비밀번호가 일치하지 않습니다.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(
            text = "이름 *",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                showEmptyFieldsError = false
            },
            label = { Text("이름을 입력하세요.",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color(0xFFB3B3B3)) },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Text(
            text = "휴대폰 번호 *",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                showEmptyFieldsError = false
            },
            label = { Text("휴대폰 번호 입력('-'제외 11자리 입력)",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color(0xFFB3B3B3)) },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )


        Text(
            text = "이메일 주소 *",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = emailUser,
                onValueChange = {
                    emailUser = it
                    showEmptyFieldsError = false
                },
                label = { Text("이메일 주소를 입력하세요.",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = Color(0xFFB3B3B3)) },
                modifier = Modifier.weight(2f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
            Text(text = "@", modifier = Modifier.align(Alignment.CenterVertically))
            OutlinedTextField(
                value = emailDomain,
                onValueChange = {
                    emailDomain = it
                    showEmptyFieldsError = false
                },
                label = { Text("") },
                modifier = Modifier.weight(2f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }

        Text(
            text = "학번 *",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        OutlinedTextField(
            value = studentId,
            onValueChange = {
                studentId = it
                showEmptyFieldsError = false
            },
            label = { Text("학번 9자리를 입력하세요",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color(0xFFB3B3B3)) },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        Text(
            text = "학과 *",
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        OutlinedTextField(
            value = department,
            onValueChange = {
                department = it
                showEmptyFieldsError = false
            },
            label = { Text("학과를 입력하세요.",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = Color(0xFFB3B3B3)) },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 20.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.padding(bottom = 15.dp))
        Button(
            onClick = {
                if (id.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() &&
                    name.isNotEmpty() && phoneNumber.isNotEmpty() &&
                    emailUser.isNotEmpty() && emailDomain.isNotEmpty() &&
                    studentId.isNotEmpty() && department.isNotEmpty() &&
                    !showError
                ) {
                    navController.navigate(Routes.LibraryGusia.route)
                } else {
                    showEmptyFieldsError = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF65A25B), // 배경색
                contentColor = Color.White // 텍스트 색상
            ),
        ) {
            Text("회원가입",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        }
        if (showEmptyFieldsError) {
            Text(
                text = "빈 칸 없이 입력해주세요.",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}