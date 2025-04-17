package com.ag.projects.taskmanager.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ag.projects.taskmanager.data.local.Priority

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriorityChip(
    priority: Priority,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val (chipText, color) = when (priority) {
        Priority.HIGH -> "High" to MaterialTheme.colorScheme.error
        Priority.MEDIUM -> "Medium" to MaterialTheme.colorScheme.tertiary
        Priority.LOW -> "Low" to MaterialTheme.colorScheme.primary
    }

    AssistChip(
        onClick = { onClick?.invoke() },
        label = { Text(text = chipText) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = color.copy(alpha = 0.2f),
            labelColor = color
        ),
        leadingIcon = {
            Row(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(color),
                horizontalArrangement = Arrangement.Center
            ){}
        },
        modifier = modifier
    )
}