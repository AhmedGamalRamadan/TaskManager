package com.ag.projects.taskmanager.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ag.projects.taskmanager.data.local.Task
import com.ag.projects.taskmanager.utils.Screen

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    task: Task,
    isCompleted: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    onDelete: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp)
            .padding(8.dp)
            .clickable {
                navHostController.navigate(
                    Screen.TaskDetails(id = task.id)
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 7.dp
        )
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(7.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
            ) {

                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = {
                        onCheckedChanged(it)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Green,
                    ),
                    modifier = modifier
                        .testTag("CheckBoxIsCompleted")
                )

                Text(
                    text = task.title,
                    modifier = modifier
                        .weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration =
                    if (task.isCompleted)
                        TextDecoration.LineThrough
                    else TextDecoration.None,
                )

                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red,
                    modifier = modifier
                        .testTag("DeleteTask")
                        .size(40.dp)
                        .clickable {
                            onDelete(task.id)
                        }
                )
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = task.priority.toString(), color = MaterialTheme.colorScheme.primary)
                Text(text = task.createdAt, color = MaterialTheme.colorScheme.primary)

            }
        }
    }
}
