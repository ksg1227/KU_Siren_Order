package com.example.teamproject.mypage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.teamproject.Component.DrawDepartmentDropdown
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
    val initialPassword = userViewModel.user.passwd
    val currentPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val name = remember { mutableStateOf(userViewModel.user.name) }
    val department = remember { mutableStateOf(userViewModel.user.department) }
    val email = remember { mutableStateOf(userViewModel.user.emailAddress) }
    val phoneNumber = remember { mutableStateOf(userViewModel.user.phoneNum) }

    var passwordMatchError by remember { mutableStateOf(false) }
    var currentPasswordError by remember { mutableStateOf(false) }
    var newPasswordError by remember { mutableStateOf(false) }

    val emailParts = email.value.split("@")
    val initialLocalPart = emailParts.getOrNull(0) ?: ""
    val initialDomainPart = emailParts.getOrNull(1) ?: ""

    var localPart by remember { mutableStateOf(initialLocalPart) }
    var domainPart by remember { mutableStateOf(initialDomainPart) }

    val fullEmail = "$localPart@$domainPart"

    LaunchedEffect(fullEmail) {
        email.value = fullEmail
        userViewModel.user.emailAddress = fullEmail
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$".toRegex()
        return passwordRegex.matches(password)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        com.example.teamproject.Screen.TopAppBar(
            onBackIconClick = { navController.popBackStack() },
            title = "개인 정보 수정",
            titleColor = Color.Black,
            onRightIconClick = { },
            rightIconImgId = null
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            UserInfoEditField(
                label = "기존 비밀번호",
                text = "기존 비밀번호를 입력하세요.",
                value = currentPassword.value,
                onValueChange = {
                    currentPassword.value = it
                    currentPasswordError = it.isNotEmpty() && it != initialPassword
                },
                isPassword = true
            )

            if (currentPasswordError) {
                Text(
                    text = "기존 비밀번호와 동일하지 않습니다.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            UserInfoEditField(
                label = "새 비밀번호",
                text = "새 비밀번호를 입력하세요.",
                value = newPassword.value,
                onValueChange = {
                    newPassword.value = it
                    newPasswordError = it.isNotEmpty() && !isPasswordValid(it)
                },
                isPassword = true
            )

            if (newPasswordError) {
                Text(
                    text = "비밀번호는 영문자, 특수문자, 숫자를 포함한 8자리 이상이어야 합니다.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            UserInfoEditField(
                label = "새 비밀번호 확인",
                text = "새 비밀번호를 한번 더 입력하세요.",
                value = confirmPassword.value,
                onValueChange = {
                    confirmPassword.value = it
                    passwordMatchError = it.isNotEmpty() && it != newPassword.value
                },
                isPassword = true
            )

            if (passwordMatchError) {
                Text(
                    text = "새 비밀번호가 일치하지 않습니다.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            UserInfoEditField(
                label = "이름",
                text = name.value,
                value = name.value,
                onValueChange = { name.value = it }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                UserInfoEditField(
                    label = "이메일",
                    text = localPart,
                    value = localPart,
                    onValueChange = { localPart = it },
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "@",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                UserInfoEditField(
                    label = "도메인",
                    text = domainPart,
                    value = domainPart,
                    onValueChange = { domainPart = it },
                    modifier = Modifier.weight(1f)
                )
            }

            UserInfoEditField(
                label = "전화번호",
                text = phoneNumber.value,
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it }
            )

            UserInfoEditField(
                label = "학과",
                text = "",
                value = department.value,
                onValueChange = {selectedDepartment ->
                    department.value = selectedDepartment}
            )


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
                    val updatedPassword = if (currentPassword.value.isEmpty() || newPassword.value.isEmpty() || confirmPassword.value.isEmpty()) {
                        initialPassword
                    } else {
                        newPassword.value
                    }

                    if (!currentPasswordError && !passwordMatchError && !newPasswordError) {
                        val user = User(
                            userViewModel.user.id,
                            updatedPassword,
                            name.value,
                            phoneNumber.value,
                            email.value,
                            userViewModel.user.studentId,
                            department.value
                        )
                        userViewModel.UpdateUser(user)
                        userViewModel.user = user
                        navController.navigate(Routes.MyPageMainScreen.route)
                    }
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
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold))
        )
        if(label == "학과") {
            DrawDepartmentDropdown(
                selectedDepartment = value,
                onDepartmentSelected = onValueChange
            )
        } else {
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
}
