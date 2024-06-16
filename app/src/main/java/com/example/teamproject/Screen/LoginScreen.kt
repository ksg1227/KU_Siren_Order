package com.example.teamproject.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.teamproject.Component.BtnRectangle
import com.example.teamproject.R
import com.example.teamproject.ViewModel.UserViewModel
import com.example.teamproject.navigation.Routes

@Composable
fun LoginScreen(navController: NavHostController, userViewModel: UserViewModel) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

//    val table = Firebase.database.getReference("UserDB/users")
//
//    val viewModel: UserViewModel =
//        viewModel(factory = UserViewModelFactory(Repository(table)))

    val userList by userViewModel.userList.collectAsState()

    val scrollState = rememberScrollState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordFocusRequester = remember { FocusRequester() }
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
                title = "로그인",
                titleColor = Color.Black,
                onRightIconClick = { /*TODO*/ },
                rightIconImgId = null
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
//                Spacer(modifier = Modifier.padding(bottom = 150.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.konkuk),
                        contentDescription = "건국대로고",
                        modifier = Modifier
                            .size(100.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "KU 레스티오/학식 사이렌 오더",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        color = Color(0xFF000000)
                    )
                }

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
                        value = id,
                        onValueChange = { id = it },
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

                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { passwordFocusRequester.requestFocus() }
                        )
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(passwordFocusRequester),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { keyboardController?.hide() }
                        )
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = errorMessage, color = MaterialTheme.colorScheme.error,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                BtnRectangle(
                    text = "로그인",
                    textColorId = R.color.white,
                    bgColorId = R.color.green_65a25b,
                    width = null
                ) {
                    if (id.isEmpty() || password.isEmpty()) {
                        errorMessage = "아이디와 패스워드를 모두 입력해주세요."
                    } else {
                        errorMessage = ""

                        var loginPossible = false

//                    userViewModel.getUsers(id, password);
                        for (user in userList) {
                            if (user.id == id && user.passwd == password) {
                                loginPossible = true
                                userViewModel.user = user
                            }
                        }

                        if (!loginPossible) {    //정보가 존재하지 않는 경우
                            errorMessage = "회원님의 정보가 존재하지 않습니다."
                        } else {
                            navController.navigate(Routes.CafeteriaRestioSelScreen.route) {
                                popUpTo(Routes.Start.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

