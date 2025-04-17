package com.ag.projects.taskmanager.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ag.projects.taskmanager.presentation.screen.create.CreateTaskScreen
import com.ag.projects.taskmanager.presentation.screen.home.HomeScreen
import com.ag.projects.taskmanager.utils.Screen

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home"){
            HomeScreen(
                navHostController = navController
            )
        }

        composable("createTask"){
            CreateTaskScreen(
                navHostController = navController
            )
        }


    }
}