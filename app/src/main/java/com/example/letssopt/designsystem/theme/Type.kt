package com.example.letssopt.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.letssopt.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.W400),
    Font(R.font.pretendard_bold, FontWeight.W700),
)

private fun customTextStyle(
    size: Int,
    weight: FontWeight
) = TextStyle(
    fontFamily = Pretendard,
    fontWeight = weight,
    fontSize = size.sp,
    lineHeight = size.sp,
    letterSpacing = 0.sp
)

val Typography = Typography(
    displayLarge = customTextStyle(36, FontWeight.W700),
    headlineLarge = customTextStyle(24, FontWeight.W700),
    headlineMedium = customTextStyle(20, FontWeight.W700),
    bodyLarge = customTextStyle(16, FontWeight.W400),
    labelLarge = customTextStyle(16, FontWeight.W700),
    labelSmall = customTextStyle(14, FontWeight.W400)
)