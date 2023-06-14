package com.loki.britam.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loki.britam.presentation.AppState

@Composable
fun Navigation(appState: AppState) {

    NavHost(navController = appState.navController, startDestination = Screens.OnBoardingScreen.route) {

        composable(route = Screens.OnBoardingScreen.route) {

        }
    }
}

sealed class Screens(val route: String) {
    object OnBoardingScreen: Screens("onboarding_screen")
    object LoginScreen: Screens("login_screen")
    object SignIn: Screens("signin_screen")
    object HomeScreen: Screens("Home_screen")
    object BizScreen: Screens("biz_screen")
    object WalletScreen: Screens("wallet_screen")
}