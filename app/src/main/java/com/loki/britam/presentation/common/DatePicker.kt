package com.loki.britam.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerDefaults
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    pickerState: DateRangePickerState,
    onSave: (Long?, Long?) -> Unit,
    onDismiss: () -> Unit
) {

    val dateFormatter = remember {
        DatePickerDefaults.dateFormatter()
    }

    Column(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onDismiss) {
                Icon(Icons.Filled.Close, contentDescription = null)
            }
            TextButton(
                onClick = {
                    onSave(pickerState.selectedStartDateMillis, pickerState.selectedEndDateMillis)
                },
                enabled = pickerState.selectedEndDateMillis != null
            ) {
                Text(text = "Save")
            }
        }

        DateRangePicker(
            state = pickerState,
            modifier = Modifier,
            dateFormatter = dateFormatter,
            title = {
                DateRangePickerDefaults.DateRangePickerTitle(
                    displayMode = pickerState.displayMode,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            },
            headline = {
                DateRangePickerDefaults.DateRangePickerHeadline(
                    selectedStartDateMillis = pickerState.selectedStartDateMillis,
                    selectedEndDateMillis = pickerState.selectedEndDateMillis,
                    displayMode = pickerState.displayMode,
                    dateFormatter = dateFormatter,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            },
            showModeToggle = true
        )
    }
}