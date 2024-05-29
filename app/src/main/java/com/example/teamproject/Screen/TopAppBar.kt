package com.example.teamproject.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teamproject.R

@Composable
fun TopAppBar(
    onBackIconClick: () -> Unit,
    title: String,
    titleColor: Color,
    onRightIconClick: (() -> Unit)?,
    rightIconImgId: Int?,
) {
    androidx.compose.material.TopAppBar(
        backgroundColor = Color.White,
        contentPadding = PaddingValues(horizontal = 5.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // 뒤로 가기(좌측 아이콘)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = onBackIconClick,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        modifier = Modifier.size(25.dp),
                        contentDescription = "뒤로 가기"
                    )
                }
            }
            // title
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 16.sp,
                    color = titleColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            // 우측 아이콘
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (rightIconImgId != null && onRightIconClick != null) {
                    IconButton(
                        onClick = onRightIconClick,
                        modifier = Modifier.align(Alignment.CenterEnd),
                    ) {
                        Image(
                            painter = painterResource(id = rightIconImgId),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "우측 아이콘"
                        )
                    }
                }
            }
        }
    }
}