package com.loki.britam.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.loki.britam.presentation.components.BottomBar
import com.loki.britam.presentation.navigation.Navigation
import com.loki.britam.presentation.theme.BritamTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
    navController: NavHostController = rememberAnimatedNavController(),
) = remember(navController) {
    AppState(
        navController = navController
    )
}
