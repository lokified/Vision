package com.loki.britam.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.loki.britam.presentation.AppState
import com.loki.britam.presentation.components.BottomBar
import com.loki.britam.presentation.navigation.Navigation
import com.loki.britam.presentation.theme.BritamTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BritamTheme {

                val appState = rememberAppState()

                Scaffold(
                    bottomBar = {
                        BottomBar(
                            navController = appState.navController,
                            onItemClick = {
                                appState.bottomNavNavigate(
                                    it.route
                                )
                            }
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        Navigation(appState = appState)
                    }
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    AppState(
        navController = navController
    )
}
