package com.example.teamproject.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.teamproject.R

@Composable
fun CustomAlertDialog(
    title: String,
    onClickToFloor: () -> Unit,
    onClickToBottom: () -> Unit,
    onClickCancel: () -> Unit
) {
    Dialog(
        onDismissRequest = { onClickCancel() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .height(230.dp)
                    .background(
                        color = Color.White,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )


                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    // Row의 높이를 내부 컴포넌트에 맞춤
                ) {
                    Button(
                        onClick = { onClickToFloor() },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(bottom = 20.dp, end = 10.dp, top = 15.dp, start = 20.dp)
                            .border(
                                width = 1.dp, color = Color.Gray
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // 버튼 배경색상
                            contentColor =  Color(0xFF444444), // 버튼 텍스트 색상
                            disabledContainerColor = Color.Gray, // 버튼 비활성화 배경 색상
                            disabledContentColor = Color.White, // 버튼 비활성화 텍스트 색상
                        ),

                        ) {
                        Text(
                            text = "학생회관       1층 학식",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }



                    Button(
                        onClick = { onClickToBottom() },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(bottom = 20.dp, end = 20.dp, top = 15.dp, start = 10.dp)
                            .border(
                                width = 1.dp, color = Color.Gray
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // 버튼 배경색상
                            contentColor = Color(0xFF444444), // 버튼 텍스트 색상
                            disabledContainerColor = Color.Gray, // 버튼 비활성화 배경 색상
                            disabledContentColor = Color.White, // 버튼 비활성화 텍스트 색상
                        ),
                    ) {
                        Text(
                            text = "학생회관    지하 학식",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)// Row의 높이를 내부 컴포넌트에 맞춤
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { onClickCancel() },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .height(40.dp)
                            .width(130.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.green_65a25b), // 버튼 배경색상
                            contentColor = Color.Black, // 버튼 텍스트 색상
                            disabledContainerColor = Color.Gray, // 버튼 비활성화 배경 색상
                            disabledContentColor = Color.White, // 버튼 비활성화 텍스트 색상
                        ),

                        ) {
                        Text(
                            text = "취소",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                }
            }

        }
    }
}