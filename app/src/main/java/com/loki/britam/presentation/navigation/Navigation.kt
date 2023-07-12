package com.loki.britam.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.loki.britam.core.Constants.TITLE
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.loki.britam.core.Constants.COMPANY_ID
import com.loki.britam.core.Constants.EXPENSE_DEFAULT_ID
import com.loki.britam.core.Constants.EXPENSE_ID
import com.loki.britam.presentation.AppState
import com.loki.britam.presentation.account.AccountScreen
import com.loki.britam.presentation.account.AccountViewModel
import com.loki.britam.presentation.biz.BizScreen
import com.loki.britam.presentation.biz.BizViewModel
import com.loki.britam.presentation.company.CompanyScreen
import com.loki.britam.presentation.company.CompanyViewModel
import com.loki.britam.presentation.company.AddEditExpenseScreen
import com.loki.britam.presentation.home.HomeScreen
import com.loki.britam.presentation.home.HomeViewModel
import com.loki.britam.presentation.products.ApplyScreen
import com.loki.britam.presentation.products.insurance.InsuranceScreen
import com.loki.britam.presentation.products.insurance.InsuranceViewModel
import com.loki.britam.presentation.login.LoginScreen
import com.loki.britam.presentation.login.LoginViewModel
import com.loki.britam.presentation.new_company.NewCompanyScreen
import com.loki.britam.presentation.new_company.NewCompanyViewModel
import com.loki.britam.presentation.onboarding.OnBoardingScreen
import com.loki.britam.presentation.onboarding.OnBoardingViewModel
import com.loki.britam.presentation.products.investments.InvestScreen
import com.loki.britam.presentation.register.RegisterScreen
import com.loki.britam.presentation.register.RegisterViewModel
import com.loki.britam.presentation.transactions.TransactionScreen
import com.loki.britam.presentation.wallet.WalletScreen
import com.loki.britam.presentation.wallet.WalletViewModel

@ExperimentalAnimationApi
@Composable
fun Navigation(appState: AppState) {

    AnimatedNavHost(
        navController = appState.navController,
        startDestination = Screens.OnBoardingScreen.route
    ) {

        composable(route = Screens.OnBoardingScreen.route) {

            val viewModel = hiltViewModel<OnBoardingViewModel>()

            OnBoardingScreen(
                viewModel = viewModel,
                openAndPop = { route, pop ->
                        appState.navigateAndPopUp(route, pop)
                },
                openScreen = { appState.navigate(it) }
            )
        }

        composable(route = Screens.LoginScreen.route) {

            val viewModel = hiltViewModel<LoginViewModel>()

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

            val viewModel = hiltViewModel<RegisterViewModel>()

            RegisterScreen(
                viewModel = viewModel,
                openScreen = { appState.navigate(it) }
            )
        }

        composable(
            route = Screens.HomeScreen.route,
            enterTransition = {
                when(initialState.destination.route) {
                    Screens.InsuranceScreen.route -> {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(800)
                        )
                    }
                    Screens.InvestScreen.route -> {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(800)
                        )
                    }
                    else -> null
                }
            },
            exitTransition = {
                when(targetState.destination.route) {
                    Screens.InsuranceScreen.route -> {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(800)
                        )
                    }
                    Screens.InvestScreen.route -> {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(800)
                        )
                    }
                    else -> null
                }
            }
        ) {

            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                openScreen = { appState.navigate(it) }
            )
        }

        composable(
            route = Screens.BizScreen.route,
            enterTransition = null,
            exitTransition = null
        ) {

            val viewModel = hiltViewModel<BizViewModel>()
            BizScreen(
                viewModel = viewModel
            )
        }

        composable(
            route = Screens.WalletScreen.route,
            enterTransition = {
                when(initialState.destination.route) {
                    Screens.TransactionsScreen.route -> {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(800)
                        )
                    }
                    else -> null
                }
            },
            exitTransition = {
                when(targetState.destination.route) {
                    Screens.TransactionsScreen.route -> {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(800)
                        )
                    }
                    else -> null
                }
            }
        ) {

            val viewModel = hiltViewModel<WalletViewModel>()
            WalletScreen(
                viewModel = viewModel,
                openScreen = { appState.navigate(it) }
            )
        }
        
        composable(
            route = Screens.InsuranceScreen.route,
            enterTransition = {
                if (initialState.destination.route == Screens.HomeScreen.route)
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(800)
                    ) else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.HomeScreen.route)
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(800)
                    ) else null
            }
        ) {

            InsuranceScreen(
                openScreen = { appState.navigate(Screens.ApplyScreen.navWithApplyTitle(it)) },
            )
        }

        composable(
            route = Screens.ApplyScreen.withApplyTitle(),
            arguments = listOf(
                navArgument(name = TITLE) {
                    type = NavType.StringType
                }
            )
        ) {
            val title = it.arguments?.getString(TITLE)!!
            ApplyScreen(title = title, viewModel = InsuranceViewModel(),popScreen = { appState.popUp() })
        }

        composable(route = Screens.NewCompanyScreen.route) {

            val viewModel = hiltViewModel<NewCompanyViewModel>()
            NewCompanyScreen(
                viewModel = viewModel,
                popScreen = { appState.popUp() }
            )
        }

        composable(
            route = Screens.CompanyScreen.withCompanyId(),
            arguments = listOf(
                navArgument(name = COMPANY_ID) {
                    type = NavType.StringType
                }
            )
        ) {

            val viewModel = hiltViewModel<CompanyViewModel>()
            CompanyScreen(
                viewModel = viewModel,
                openScreen = { appState.navigate(it) }
            )
        }

        composable(
            route = Screens.AddEditExpenseScreen.withExpenseId(),
            arguments = listOf(
                navArgument(EXPENSE_ID) {
                    type = NavType.StringType
                }
            )
        ) {

            val viewModel = hiltViewModel<CompanyViewModel>()

            AddEditExpenseScreen(
                viewModel = viewModel,
                popScreen = { appState.popUp() }
            )
        }

        composable(
            route = Screens.TransactionsScreen.route,
            enterTransition = {
                if (initialState.destination.route == Screens.WalletScreen.route)
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(800)
                    ) else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.WalletScreen.route)
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(800)
                    ) else null
            }
        ) {

            TransactionScreen(
                popScreen = { appState.popUp() }
            )
        }

        composable(
            route = Screens.InvestScreen.route,
            enterTransition = {
                if (initialState.destination.route == Screens.HomeScreen.route)
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(800)
                    ) else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.HomeScreen.route)
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(800)
                    ) else null
            }
        ) {

            InvestScreen(
                openScreen = { appState.navigate(Screens.ApplyScreen.navWithApplyTitle(it)) },
            )
        }

        composable(route = Screens.AccountScreen.route) {
            
            val viewModel = hiltViewModel<AccountViewModel>()
            
            AccountScreen(
                viewModel = viewModel,
                openAndStart = { appState.clearAndNavigate(it) })
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
    object InsuranceScreen: Screens("insurance_screen")
    object ApplyScreen: Screens("apply_screen")
    object NewCompanyScreen: Screens("new_company_screen")
    object CompanyScreen: Screens("company_screen")
    object AddEditExpenseScreen: Screens("add_edit_expense_screen")
    object TransactionsScreen: Screens("transactions_screen")
    object InvestScreen: Screens("invest_screen")
    object AccountScreen: Screens("account_screen")

    fun withApplyTitle(): String = "${ApplyScreen.route}/{$TITLE}"

    fun navWithApplyTitle(title: String): String = "${ApplyScreen.route}/$title"

    fun withCompanyId(): String ="${CompanyScreen.route}/{$COMPANY_ID}"

    fun navWithCompanyId(id: String): String = "${CompanyScreen.route}/$id"

    fun withExpenseId(): String = "${AddEditExpenseScreen.route}/{$EXPENSE_ID}"

    fun navWithExpenseId(id: String): String = "${AddEditExpenseScreen.route}/$id"
}