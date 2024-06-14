package com.example.teamproject.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.teamproject.Item.User
import com.example.teamproject.R
import com.example.teamproject.ViewModel.LocalNavGraphViewModelStoreOwner
import com.example.teamproject.ViewModel.UserViewModel
import com.example.teamproject.navigation.Routes


@Composable
fun MyPageEditScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)
    ) {
    val currentPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val department = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // 상단
        com.example.teamproject.Screen.TopAppBar(
            onBackIconClick = { navController.popBackStack() },
            title = "개인 정보 수정",
            titleColor = Color.Black,
            onRightIconClick = {  },
            rightIconImgId = null
        )

        // 내용
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
//            Image(
//                painter = painterResource(id = R.drawable.konkuk), // Placeholder for profile image
//                contentDescription = null,
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(CircleShape)
//            )
//            Spacer(modifier = Modifier.height(16.dp))
            UserInfoEditField(
                label = "기존 비밀번호",
                text = "기존 비밀번호를 입력하세요.",
                value = currentPassword.value,
                onValueChange = { currentPassword.value = it },
                isPassword = true
            )

            UserInfoEditField(
                label = "새 비밀번호",
                text = "새 비밀번호를 입력하세요.",
                value = newPassword.value,
                onValueChange = { newPassword.value = it },
                isPassword = true
            )
            UserInfoEditField(
                label = "새 비밀번호 확인",
                text = "새 비밀번호를 한번 더 입력하세요.",
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                isPassword = true
            )

            UserInfoEditField(label = "이름",
                text = userViewModel.user.name,
                value = name.value,
                onValueChange = { name.value = it })
            UserInfoEditField(label = "학과",
                text = userViewModel.user.department,
                value = department.value,
                onValueChange = { department.value = it })
            UserInfoEditField(label = "이메일",
                text = userViewModel.user.emailAddress,
                value = email.value,
                onValueChange = { email.value = it })
            UserInfoEditField(label = "전화번호",
                text = userViewModel.user.phoneNum,
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it })
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /* 취소 버튼 클릭 처리 */ }) {
                    Text(text = "취소", color = Color.Gray)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    val user = User(userViewModel.user.id, newPassword.value,name.value,phoneNumber.value,email.value, userViewModel.user.studentId, department.value)
                    userViewModel.UpdateUser(user)
                    userViewModel.user = user
                    navController.navigate(Routes.MyPageMainScreen.route)
                }) {
                    Text(text = "저장")
                }
            }
        }
    }
}


@Composable
fun UserInfoEditField(
    label: String,
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = text,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = Color(0xFFB3B3B3)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
    }
}


