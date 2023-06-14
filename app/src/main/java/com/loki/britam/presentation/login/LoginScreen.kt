package com.loki.britam.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.loki.britam.presentation.theme.BritamTheme

@Composable
fun LoginScreen(
    openScreen: (String) -> Unit,
    openAndPopScreen: (String, String) -> Unit
) {

    
}

@Preview(
    showBackground = true
)
@Composable
fun LoginScreenPreview() {
    BritamTheme {
        LoginScreen(openScreen = {}, openAndPopScreen = {_, _ ->})
    }
}