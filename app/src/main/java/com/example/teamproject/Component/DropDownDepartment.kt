package com.example.teamproject.Component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teamproject.R

@Composable
fun DrawDepartmentDropdown(
    selectedDepartment: String,
    onDepartmentSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val departments = listOf(
        // 문과대학
        "국어국문학과", "영어영문학과", "중어중문학과", "철학과", "사학과", "지리학과", "미디어커뮤니케이션학과", "문화콘텐츠학과",
        // 이과대학
        "수학과", "물리학과", "화학과",
        // 건축대학
        "건축학부",
        // 공과대학
        "사회환경공학부", "기계항공공학부", "전기전자공학부", "화학공학부", "컴퓨터공학부",
        "산업경영공학부 산업공학과", "산업경영공학부 신산업융합학과", "생물공학과", "K뷰티산업융합학과",
        // 사회과학대학
        "정치외교학과", "경제학과", "행정학과", "국제무역학과", "응용통계학과", "융합인재학과", "글로벌비즈니스학과",
        // 경영대학
        "경영학과", "기술경영학과",
        // 부동산과학원
        "부동산학과",
        // KU융합과학기술원
        "미래에너지공학과", "스마트운행체공학과", "스마트ICT융합공학과", "화장품공학과",
        "줄기세포재생공학과", "의생명공학과", "시스템생명공학과", "융합생명공학과",
        // 상허생명과학대학
        "생명과학특성학과", "동물자원과학과", "식량자원과학과", "축산식품생명공학과",
        "식품유통공학과", "환경보건과학과", "산림조경학과",
        // 수의과대학
        "수의예과", "수의학과",
        // 예술디자인대학
        "커뮤니케이션디자인학과", "산업디자인학과", "의상디자인학과", "리빙디자인학과",
        "현대미술학과", "영상영화학과",
        // 사범대학
        "일어교육과", "수학교육과", "체육교육과", "음악교육과", "교육공학과", "영어교육과", "교직과/대학원교육학과"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
                .border(width = 0.5.dp, color = Color.Black, shape = RoundedCornerShape(5.dp))
                .padding(start = 15.dp, end = 15.dp, top = 17.dp, bottom = 17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (selectedDepartment.isEmpty()) "전공학과 선택" else selectedDepartment,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                color = if(selectedDepartment.isEmpty()) Color(0xFFB3B3B3) else Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Color.Black
            )
        }
        DropdownMenu(
            modifier = Modifier,
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            departments.forEach { department ->
                DropdownMenuItem(
                    onClick = {
                        onDepartmentSelected(department)
                        expanded = false
                    }
                ) {
                    Text(text = department,
                        color = Color.Black)
                }
            }
        }
    }
}