package com.omaradev.core_ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.core_ui.resources.Res
import com.omaradev.core_ui.resources.ic_info_red
import com.omaradev.core_ui.theme.ColorErrorRed
import com.omaradev.core_ui.theme.InfoBgColor
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorView(
    errorId: StringResource?,
    isError: Boolean,
    modifier: Modifier = Modifier
) {

    val density = LocalDensity.current
    AnimatedVisibility(
        visible = isError && errorId != null,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Row(
            modifier = modifier
                .padding(top = 2.dp)
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = InfoBgColor
                )
                .padding(start = 2.dp, top = 2.dp, bottom = 2.dp, end = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_info_red),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 2.dp)
                    .size(15.dp)
            )

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = errorId?.let { stringResource(it) } ?: "",
                style = TextStyle(
                    color = ColorErrorRed,
                    fontSize = 12.sp
                )
            )
        }
    }
}