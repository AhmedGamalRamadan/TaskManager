package com.ag.projects.taskmanager.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskManagerTopAppBar(
    modifier: Modifier = Modifier,
    darkTheme: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    onThemeUpdated: () -> Unit
) {

    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "TaskManager",
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
            ThemeSwitcher(
                darkTheme = darkTheme,
                padding = 8.dp,
                onClick = {
                    onThemeUpdated()
                }
            )
        }
    )
}