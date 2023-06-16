package com.loki.britam.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loki.britam.presentation.AppState
import com.loki.britam.presentation.biz.BizScreen
import com.loki.britam.presentation.biz.BizViewModel
import com.loki.britam.presentation.home.HomeScreen
import com.loki.britam.presentation.home.HomeViewModel
import com.loki.britam.presentation.login.LoginScreen
import com.loki.britam.presentation.login.LoginViewModel
import com.loki.britam.presentation.onboarding.OnBoardingScreen
import com.loki.britam.presentation.register.RegisterScreen
import com.loki.britam.presentation.register.RegisterViewModel
import com.loki.britam.presentation.wallet.WalletScreen
import com.loki.britam.presentation.wallet.WalletViewModel

@Composable
fun Navigation(appState: AppState) {

    NavHost(
        navController = appState.navController,
        startDestination = Screens.OnBoardingScreen.route
    ) {

        composable(route = Screens.OnBoardingScreen.route) {
            OnBoardingScreen(openScreen = { appState.navigate(it) })
        }

        composable(route = Screens.LoginScreen.route) {

            val viewModel = LoginViewModel()

            LoginScreen(
                viewModel = viewModel,
                openScreen = { route ->
                    appState.navigate(route)
                },
                openAndPopScreen = { route, popup ->
                    appState.navigateAndPopUp(route, popup)
                }
            )
        }

        composable(route = Screens.RegisterScreen.route) {

            val viewModel = RegisterViewModel()

            RegisterScreen(
                viewModel = viewModel,
                openScreen = { appState.navigate(it) }
            )
        }

        composable(route = Screens.HomeScreen.route) {

            val viewModel = HomeViewModel()
            HomeScreen(
                viewModel = viewModel
            )
        }

        composable(route = Screens.BizScreen.route) {

            val viewModel = BizViewModel()
            BizScreen(
                viewModel = viewModel
            )
        }

        composable(route = Screens.WalletScreen.route) {

            val viewModel = WalletViewModel()
            WalletScreen(viewModel)
        }
    }
}

sealed class Screens(val route: String) {
    object OnBoardingScreen: Screens("onboarding_screen")
    object LoginScreen: Screens("login_screen")
    object ForgotPasswordScreen: Screens("forgot_screen")
    object RegisterScreen: Screens("register_screen")
    object HomeScreen: Screens("Home_screen")
    object BizScreen: Screens("biz_screen")
    object WalletScreen: Screens("wallet_screen")
}