package com.omaradev.todo_ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.omaradev.todo_domain.models.TimelineItem

@Composable
fun TimelineCard(
    item: TimelineItem,
    isLast: Boolean,
    ifFirst: Boolean
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val (dot, line, card) = createRefs()

        if (ifFirst) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White, CircleShape)
                    .border(2.dp, Color.Red, CircleShape)
                    .constrainAs(dot) {
                        top.linkTo(card.top)
                        start.linkTo(parent.start)
                    }
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(18.dp)
                        .padding(2.dp)
                        .background(Color.Red, CircleShape)
                )
            }
        } else
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(Color.White, CircleShape)
                    .border(2.dp, Color.Red, CircleShape)
                    .constrainAs(dot) {
                        top.linkTo(card.top)
                        start.linkTo(parent.start)
                    }
            )

        if (!isLast) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .background(Color.Red.copy(alpha = 0.6f))
                    .constrainAs(line) {
                        top.linkTo(dot.bottom)
                        bottom.linkTo(card.bottom)
                        start.linkTo(dot.start)
                        end.linkTo(dot.end)
                        height = Dimension.fillToConstraints
                    }
            )
        }

        Column(
            modifier = Modifier.constrainAs(card) {
                top.linkTo(parent.top)
                start.linkTo(dot.end, margin = 12.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(item.backgroundColorResource).copy(alpha = 0.3f))
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = item.time,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}