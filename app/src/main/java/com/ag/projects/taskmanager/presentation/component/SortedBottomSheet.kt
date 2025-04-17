@file:OptIn(ExperimentalMaterial3Api::class)

package com.ag.projects.taskmanager.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ag.projects.taskmanager.data.local.TaskSort
import kotlinx.coroutines.launch

@Composable
fun SortedBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    sortedOptions: List<TaskSort>,
    checked: Boolean,
    taskSort: TaskSort,
    onCheckedChanged: (Boolean) -> Unit,
    onOptionsSelected: (TaskSort) -> Unit
) {

    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(10.dp),
        sheetState = sheetState,
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
            }
        }
    ) {

        sortedOptions.forEach { option ->

            Row(
                modifier = modifier
                    .fillMaxWidth(),
            ) {

                Text(
                    text = option.name,
                    modifier = modifier
                        .weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 22.sp,
                )

                Checkbox(
                    checked = option == taskSort,
                    onCheckedChange = {
                        onCheckedChanged(it)
                        onOptionsSelected(option)
                        scope.launch {
                            sheetState.hide()
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Green,
                    )
                )
            }
            Spacer(modifier.height(10.dp))
        }
    }
}