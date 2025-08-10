package com.omaradev.core_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.omaradev.core_ui.resources.Res
import com.omaradev.core_ui.resources.font_medium
import com.omaradev.core_ui.resources.font_regular
import com.omaradev.core_ui.resources.font_semi_bold
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font


@OptIn(ExperimentalResourceApi::class)
@Composable
fun appTypography() = Typography(
    displayLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(Res.font.font_medium, FontWeight.Medium)),
        lineHeight = 32.sp
    ), displayMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(Res.font.font_semi_bold, FontWeight.SemiBold)),
        lineHeight = 28.sp
    ), displaySmall = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(Res.font.font_semi_bold, FontWeight.SemiBold)),
        lineHeight = 26.sp
    ),

    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(Res.font.font_regular, FontWeight.Normal)),
        lineHeight = 24.sp
    ),

    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(Res.font.font_regular, FontWeight.Normal)),
        lineHeight = 20.sp
    ),

    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(Res.font.font_regular, FontWeight.Normal)),
        lineHeight = 18.sp
    ),

    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(Res.font.font_medium, FontWeight.Medium)),
        lineHeight = 20.sp
    ), labelMedium = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(Res.font.font_medium, FontWeight.Medium)),
        lineHeight = 16.sp
    ), labelSmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(Res.font.font_medium, FontWeight.Medium)),
        lineHeight = 14.sp
    )
)
