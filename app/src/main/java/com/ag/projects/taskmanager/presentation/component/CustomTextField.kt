package com.ag.projects.taskmanager.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ag.projects.taskmanager.presentation.ui.theme.Black
import com.ag.projects.taskmanager.presentation.ui.theme.White

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            focusedTextColor = Black,
            unfocusedTextColor = Black,
            cursorColor = Black
        ),
        label = { Text(text = label) },
        shape = RoundedCornerShape(15.dp)
    )
}