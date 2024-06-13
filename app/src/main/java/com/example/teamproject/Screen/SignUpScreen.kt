package com.example.teamproject.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.teamproject.Item.User
import com.example.teamproject.R
import com.example.teamproject.ViewModel.UserViewModel
import com.example.teamproject.navigation.Routes

@Composable
fun SignUpScreen(navController: NavHostController, userViewModel: UserViewModel) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var emailUser by remember { mutableStateOf("") }
    var emailDomain by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var passwordRegexShowError by remember { mutableStateOf(false) }
    var passwordMatchShowError by remember { mutableStateOf(false) }
    var idShowError by remember { mutableStateOf(false) }
    var idValidMessage by remember { mutableStateOf("") }
    var phoneNumberShowError by remember { mutableStateOf(false) }
    var studentIdShowError by remember { mutableStateOf(false) }
    var showEmptyFieldsError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }
    var isIdChecked by remember { mutableStateOf(false) }  // Check if ID has been validated

    val scrollState = rememberScrollState()

    val userList by userViewModel.userList.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TopAppBar(
                onBackIconClick = { navController.popBackStack() },
                title = "회원가입",
                titleColor = Color.Black,
                onRightIconClick = { /*TODO*/ },
                rightIconImgId = null
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.padding(bottom = 40.dp))

                Text(
                    text = "아이디 *",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = id,
                        onValueChange = {
                            id = it
                            idValidMessage = ""
                            idShowError = false
                            showEmptyFieldsError = false
                            isIdChecked = false  // Reset the ID check status
                        },
                        label = {
                            Text(
                                "아이디를 입력하세요.",
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                color = Color(0xFFB3B3B3)
                            )
                        },
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        singleLine = true,
                        isError = idShowError
                    )
                    Button(
                        onClick = {
                            var possibleId = true
                            for (user in userList) {
                                if (user.id == id) {
                                    possibleId = false
                                    break
                                }
                            }
                            if (possibleId) {
                                idValidMessage = "유효한 아이디입니다."
                                idShowError = false
                                isIdChecked = true  // Mark the ID as validated
                            } else {
                                idValidMessage = "이미 존재하는 아이디입니다."
                                idShowError = true
                                isIdChecked = false  // Mark the ID as invalid
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE6E6E6),
                            contentColor = Color.Black
                        ),
                        shape = MaterialTheme.shapes.small.copy(all = CornerSize(8.dp)),
                        modifier = Modifier
                            .height(64.dp)
                            .padding(top = 6.dp)
                    ) {
                        Text("중복 확인")
                    }
                }
                if (idValidMessage.isNotEmpty()) {
                    Text(
                        text = idValidMessage,
                        color = if (idShowError) MaterialTheme.colorScheme.error else Color(
                            0xFF65A25B
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "비밀번호 *",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordRegexShowError = !isPasswordValid(password)
                        passwordMatchShowError =
                            confirmPassword.isNotEmpty() && password != confirmPassword
                        showEmptyFieldsError = false
                    },
                    label = {
                        Text(
                            "비밀번호를 입력하세요.",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            color = Color(0xFFB3B3B3)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    isError = passwordRegexShowError,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
                if (passwordRegexShowError) {
                    Text(
                        text = "영문자, 특수문자, 숫자를 포함한 8자리 이상을 입력해주세요.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "비밀번호 확인 *",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        passwordMatchShowError = password != it && it.isNotEmpty()
                        showEmptyFieldsError = false
                    },
                    label = {
                        Text(
                            "비밀번호를 한 번 더 입력하세요.",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            color = Color(0xFFB3B3B3)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    isError = passwordMatchShowError,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
                if (passwordMatchShowError) {
                    Text(
                        text = "비밀번호가 일치하지 않습니다.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

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
                    label = {
                        Text(
                            "이름을 입력하세요.",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            color = Color(0xFFB3B3B3)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "휴대폰 번호 *",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                        phoneNumberShowError = phoneNumber.length != 11
                        showEmptyFieldsError = false
                    },
                    label = {
                        Text(
                            "휴대폰 번호 입력('-'제외 11자리 입력)",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            color = Color(0xFFB3B3B3)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    isError = phoneNumberShowError,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
                if (phoneNumberShowError) {
                    Text(
                        text = "휴대폰 번호는 11자리여야 합니다.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "이메일 *",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = emailUser,
                        onValueChange = {
                            emailUser = it
                            showEmptyFieldsError = false
                        },
                        label = {
                            Text(
                                "이메일",
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                color = Color(0xFFB3B3B3)
                            )
                        },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    Text(
                        text = "@",
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    OutlinedTextField(
                        value = emailDomain,
                        onValueChange = {
                            emailDomain = it
                            showEmptyFieldsError = false
                        },
                        label = {
                            Text(
                                "도메인",
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                                color = Color(0xFFB3B3B3)
                            )
                        },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "학번 *",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold))
                )
                OutlinedTextField(
                    value = studentId,
                    onValueChange = {
                        studentId = it
                        studentIdShowError = studentId.length != 9
                        showEmptyFieldsError = false
                    },
                    label = {
                        Text(
                            "학번을 입력하세요.",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            color = Color(0xFFB3B3B3)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = studentIdShowError,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
                if (studentIdShowError) {
                    Text(
                        text = "학번은 9자리여야 합니다.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

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
                    label = {
                        Text(
                            "학과를 입력하세요.",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            color = Color(0xFFB3B3B3)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (id.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                            name.isEmpty() || phoneNumber.isEmpty() || emailUser.isEmpty() ||
                            emailDomain.isEmpty() || studentId.isEmpty() || department.isEmpty()
                        ) {
                            errorText = "모든 필수 입력란을 작성해 주세요."
                            showEmptyFieldsError = true
                            return@Button
                        }
                        if (!isIdChecked) {
                            errorText = "아이디 중복 확인을 해주세요."
                            idShowError = true
                            showEmptyFieldsError = true
                            return@Button
                        }
                        if (passwordRegexShowError || passwordMatchShowError || idShowError ||
                            phoneNumberShowError || studentIdShowError
                        ) {
                            errorText = "입력한 값들을 다시 확인해 주세요."
                            showEmptyFieldsError = true
                            return@Button
                        }

                        val user = User(
                            id,
                            password,
                            name,
                            phoneNumber,
                            "$emailUser@$emailDomain",
                            studentId,
                            department
                        )
                        userViewModel.InsertUser(user)
                        navController.navigate(Routes.Start.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF65A25B), // 배경색
                        contentColor = Color.White // 텍스트 색상
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "회원가입",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium))
                    )
                }

                if (showEmptyFieldsError) {
                    Text(
                        text = errorText,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

fun isPasswordValid(password: String): Boolean {
    val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$".toRegex()

    return passwordRegex.matches(password)
}
