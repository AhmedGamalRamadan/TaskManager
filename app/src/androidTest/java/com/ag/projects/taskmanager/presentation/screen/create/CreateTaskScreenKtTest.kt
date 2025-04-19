package com.ag.projects.taskmanager.presentation.screen.create

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

class CreateTaskScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun clickOnFabToCreateTaskWithHighPriority() {

        composeTestRule.onNodeWithContentDescription("Add").performClick()

        composeTestRule.onNodeWithTag("Title").performTextInput("Test Title Task")
        composeTestRule.onNodeWithTag("Description").performTextInput("This is a description for test task")

        // Select priority (e.g. HIGH)
        composeTestRule.onNodeWithText("HIGH").performClick()

        composeTestRule.onNodeWithText("Save").performClick()

        composeTestRule.onNodeWithText("All").performClick()
        composeTestRule.onNodeWithText("Test Title Task").assertIsDisplayed()
    }

}