package com.omaradev.core_ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.core_ui.theme.ColorGrayDark
import com.omaradev.core_ui.theme.ColorGrayMedium
import com.omaradev.core_ui.theme.ColorPrimary
import com.omaradev.core_ui.theme.Transparent
import com.omaradev.core_ui.ErrorView
import com.omaradev.core_ui.resources.Res
import com.omaradev.core_ui.resources.font_medium
import com.omaradev.core_ui.resources.font_semi_bold
import com.omaradev.core_ui.theme.appTypography
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.StringResource

@Composable
fun AppTextInput(
    modifier: Modifier = Modifier,
    hint: String? = null,
    placeholder: String? = null,
    defaultValue: String? = null,
    onValueChange: (value: String) -> Unit,
    errorStringResId: StringResource? = null,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = Int.MAX_VALUE,
) {
    var value by rememberSaveable(defaultValue) { mutableStateOf(defaultValue ?: "") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
    ) {
        hint?.let {
            AppLabelText(
                modifier = Modifier.fillMaxWidth(),
                text = hint
            )

            Spacer(modifier = Modifier.height(6.dp))
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, ColorGrayDark.copy(alpha = 0.2f))
        ) {
            OutlinedTextField(
                placeholder = {
                    placeholder?.let {
                        Text(
                            text = placeholder,
                            color = ColorGrayMedium,
                            style = appTypography().bodyMedium,
                            fontFamily = FontFamily(Font(Res.font.font_medium, FontWeight.Medium))
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                value = value,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = imeAction, keyboardType = keyboardType
                ),
                onValueChange = {
                    if (it.length <= maxLength) {
                        value = it
                        onValueChange(it)
                    }
                },
                shape = RoundedCornerShape(32.dp),
                colors = OutlinedTextFieldDefaults.colors().copy(
                    focusedTextColor = ColorPrimary,
                    unfocusedTextColor = ColorPrimary,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    errorIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(Res.font.font_semi_bold))
                ),
            )
        }

        AnimatedVisibility(
            visible = errorStringResId != null,
        ) {
            ErrorView(
                modifier = Modifier.padding(horizontal = 2.dp),
                errorId = errorStringResId,
                isError = true
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
