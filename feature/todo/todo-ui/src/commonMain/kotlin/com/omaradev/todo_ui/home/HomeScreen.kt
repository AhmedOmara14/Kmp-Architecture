package com.omaradev.todo_ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.omaradev.core_ui.components.AppLabelText
import com.omaradev.core_ui.theme.ColorPrimary
import com.omaradev.core_ui.theme.WhiteColor
import com.omaradev.todo_domain.models.TimelineItem
import com.omaradev.todo_ui.home.component.TimelineCard
import com.omaradev.todo_ui.resources.Res
import com.omaradev.todo_ui.resources.add_new_task
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen() {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
                .background(
                    color = ColorPrimary, shape = RoundedCornerShape(32.dp)
                )
        ) {
            Row(
                modifier = Modifier.background(ColorPrimary)
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = WhiteColor
                )
                AppLabelText(
                    text = stringResource(Res.string.add_new_task),
                    color = WhiteColor,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        val items = listOf(
            TimelineItem(
                "Wireframing",
                "12:00 PM",
                "Make some ideation from sketch and wireframes.",
                0xFFFF6B6B
            ),
            TimelineItem(
                "UI Design",
                "1:30 PM",
                "Visual design from the wireframe and make design system.",
                0xFFB5C7FF
            ),
            TimelineItem(
                "Prototyping",
                "3:00 PM",
                "Make the interactive prototype for the testing & stakeholders.",
                0xFFFFE29D
            ),
            TimelineItem(
                "Usability Testing",
                "3:45 PM",
                "Primary user testing and usability testing of the prototype.",
                0xFFC6F6D5
            ),
            TimelineItem(
                "Meeting",
                "4:30 PM",
                "Sum up discussion for the new product with stakeholders.",
                0xFFFECACA
            )
        )

        LazyColumn {
            itemsIndexed(items) { index, item ->
                TimelineCard(
                    item = item,
                    ifFirst = index == 0,
                    isLast = index == items.lastIndex,
                )
            }
        }
    }
}