package com.loki.britam.presentation

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.loki.britam.presentation.navigation.Screens

@Stable
class AppState(
    val navController: NavHostController
) {

    fun popUp() {
        navController.popBackStack()
    }


    fun navigate(route: String) {
        navController.navigate(route = route) {
            launchSingleTop = true
        }
    }

    fun bottomNavNavigate(route: String) {
        navController.navigate(route = route) {
            launchSingleTop = true
            popUpTo(Screens.HomeScreen.route) {
                saveState = true
            }
            restoreState = true
        }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) {
                inclusive = true
            }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}