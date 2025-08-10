package com.omaradev.core_ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.omaradev.core_ui.theme.AppTypography
import com.omaradev.core_ui.theme.ColorPrimary

@Composable
fun AppLabelText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = ColorPrimary,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight? = null
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        style = AppTypography.labelLarge,
        fontWeight = fontWeight
    )
}
