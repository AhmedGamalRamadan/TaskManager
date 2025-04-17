package com.ag.projects.taskmanager.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ag.projects.taskmanager.data.local.Task

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    task: Task,
    isChecked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    onDelete: (Int) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),

        ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                onCheckedChanged(it)
            }
        )

        Text(
            text = task.title,
            modifier = modifier
                .weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 22.sp
        )

        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete",
            tint = Color.Red,
            modifier = modifier
                .clickable {
                    onDelete(task.id)
                }
        )
    }
}

@Preview
@Composable
private fun TaskItem() {
    TaskItem(
        navHostController = rememberNavController(),
        task = Task(
            title = "title one for tasks",
            description = "",
            isCompleted = false,
        ),
        isChecked = false,
        onCheckedChanged = {}
    )
}