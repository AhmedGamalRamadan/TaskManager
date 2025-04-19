package com.ag.projects.taskmanager.presentation.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.ag.projects.taskmanager.presentation.activities.MainActivity
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun clickOnFabToNavigatesToCreateTaskScreen() {

        composeTestRule.onNodeWithText("All").assertIsDisplayed()

        // Tap FAB to navigate
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithTag("Title").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Description").assertIsDisplayed()
    }

    @Test
    fun filterThePendingTasks() {
        composeTestRule.onNodeWithContentDescription("Add").performClick()

        //Add title and description
        composeTestRule.onNodeWithTag("Title").performTextInput("Test Title Task")
        composeTestRule.onNodeWithTag("Description")
            .performTextInput("This is a description for test task")

        // Select priority (e.g. HIGH)
        composeTestRule.onNodeWithText("HIGH").performClick()

        composeTestRule.onNodeWithText("Save").performClick()

        composeTestRule.onNodeWithTag("Pending").performClick()

        composeTestRule.onNodeWithText("Test Title Task").assertIsDisplayed()

    }

    @Test
    fun addTaskAndSortByTitle() {
        //First add new task
        composeTestRule.onNodeWithContentDescription("Add").performClick()

        composeTestRule.onNodeWithTag("Title").performTextInput("AAAAAAAAA")
        composeTestRule.onNodeWithTag("Description")
            .performTextInput("This is a description for test task")

        composeTestRule.onNodeWithText("Save").performClick()

        composeTestRule.onNodeWithTag("Sort By Title").performClick()
        composeTestRule.onNodeWithText("AAAAAAAAA").assertIsDisplayed()

    }
}
