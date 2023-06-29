package com.loki.britam.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DropDownInput(
    modifier: Modifier = Modifier,
    placeholder: String,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    options: List<String>,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var isExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

    Column(
        modifier = modifier
    ) {

        Box (
            modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary.copy(.05f),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            TextField(
                value = selectedValue,
                onValueChange = {
                    onValueChange(it)
                },
                placeholder = {
                    Text(text = placeholder)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        textFieldSize = it.size.toSize()
                    },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(.05f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default,
                trailingIcon = {
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(imageVector = icon, contentDescription = null)
                    }
                }
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable {
                        isExpanded = !isExpanded
                    }
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.width(
                with(LocalDensity.current) {
                    textFieldSize.width.toDp()
                }
            )
        ) {
            options.forEach {
                DropdownMenuItem(
                    onClick = {
                        keyboardController?.hide()
                        onValueChange(it)
                        isExpanded = false
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
}