package com.ag.projects.taskmanager.presentation.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.core.content.edit
import com.ag.projects.taskmanager.presentation.component.TaskManagerTopAppBar
import com.ag.projects.taskmanager.presentation.navigation.Navigation
import com.ag.projects.taskmanager.presentation.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themeSharedPreferences =
            getSharedPreferences("app_theme", Context.MODE_PRIVATE)

        setContent {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            var themeState by remember {
                mutableStateOf(
                    themeSharedPreferences.getBoolean("theme", false)
                )
            }

            TaskManagerTheme(darkTheme = themeState) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TaskManagerTopAppBar(
                            darkTheme = themeState,
                            scrollBehavior = scrollBehavior,
                            onThemeUpdated = {
                                themeState = !themeState
                                themeSharedPreferences.edit {
                                    putBoolean("theme", themeState)
                                }
                            }
                        )
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Navigation()
                    }
                }
            }
        }
    }
}