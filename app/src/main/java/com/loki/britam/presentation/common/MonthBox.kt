package com.loki.britam.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.loki.britam.util.MonthUtils

@Composable
fun MonthBox(
    isExpanded: Boolean,
    onClick: (String) -> Unit,
    onDismiss: () -> Unit
) {

    val months = MonthUtils.months

    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        modifier = Modifier.height(300.dp),
        scrollState = rememberScrollState(),
        offset = DpOffset(-40.dp, -40.dp)
    ) {
        months.forEach {

            DropdownMenuItem(
                onClick = {
                    onClick(it)
                },
                text = {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 12.dp
                            ),
                    )
                }
            )
        }
    }
}