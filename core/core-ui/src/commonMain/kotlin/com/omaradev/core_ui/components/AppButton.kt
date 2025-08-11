package com.omaradev.core_ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.omaradev.core_ui.theme.ColorGray
import com.omaradev.core_ui.theme.ColorPrimary

private const val SpringStiffness = Spring.StiffnessMediumLow

@Composable
fun AppButton(
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    value: String,
    loading: Boolean = false,
    enabled: Boolean = true,
    gradient: Brush = Brush.verticalGradient(
        colors = listOf(ColorPrimary, ColorPrimary)
    ),
    onClick: () -> Unit
) {
    val transition = updateTransition(
        targetState = loading,
        label = null,
    )

    val contentPadding by transition.animateDp(
        transitionSpec = {
            spring(
                stiffness = SpringStiffness,
            )
        },
        targetValueByState = { toLoading -> if (toLoading) 12.dp else 18.dp },
        label = "",
    )

    val modifierWithBackgroundColor = when {
        enabled -> modifier
            .padding(top = 16.dp)
            .background(
                brush = gradient, shape = RoundedCornerShape(32.dp)
            )
            .defaultMinSize(minWidth = 1.dp)

        else -> modifier
            .padding(top = 16.dp)
            .background(
                color = ColorGray, shape = RoundedCornerShape(32.dp)
            )
            .defaultMinSize(minWidth = 1.dp)
    }

    Button(
        enabled = enabled && loading.not(),
        onClick = onClick,
        modifier = modifierWithBackgroundColor,
        contentPadding = PaddingValues(
            horizontal = contentPadding,
            vertical = 16.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp).defaultMinSize(minWidth = 100.dp),
            verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center
        ) {
            LoadingContent(
                loadingStateTransition = transition,
            )
            PrimaryContent(
                mainText = value,
                loadingStateTransition = transition,
                textColor = textColor
            )
        }
    }
}

@Composable
private fun LoadingContent(
    loadingStateTransition: Transition<Boolean>,
) {
    loadingStateTransition.AnimatedVisibility(
        visible = { loading -> loading },
        enter = fadeIn(),
        exit = fadeOut(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = 0.10f,
            ),
        ),
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(18.dp),
                color = Color.White,
                strokeWidth = 1.5f.dp,
                strokeCap = StrokeCap.Round,
            )
        }

    }
}


@Composable
private fun PrimaryContent(
    loadingStateTransition: Transition<Boolean>,
    mainText: String,
    textColor: Color = Color.White,
) {
    loadingStateTransition.AnimatedVisibility(
        visible = { loading -> !loading },
        enter = fadeIn() + expandHorizontally(
            animationSpec = spring(
                stiffness = SpringStiffness,
                dampingRatio = Spring.DampingRatioMediumBouncy,
                visibilityThreshold = IntSize.VisibilityThreshold,
            ),
            expandFrom = Alignment.CenterHorizontally,
        ),
        exit = fadeOut(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = 0.10f,
            ),
        ) + shrinkHorizontally(
            animationSpec = spring(
                stiffness = SpringStiffness,
                visibilityThreshold = IntSize.VisibilityThreshold,
            ),
            shrinkTowards = Alignment.CenterHorizontally,
        ),
    ) {
            AppLabelText(
                text = mainText,
                color = textColor,
                textAlign = TextAlign.Center,
            )
    }
}
