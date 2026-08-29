package com.example.androidapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidapp.feature.auth.presentation.login.LoginScreen
import com.example.androidapp.feature.home.HomeScreen

sealed class AppRoute(
    val route: String
) {

    data object Login : AppRoute("login")

    data object Home : AppRoute("home")
}

@Composable
fun AppNavGraph() {

    val navController =
        rememberNavController()

    NavHost(
        navController = navController,
        startDestination =
            AppRoute.Login.route
    ) {

        composable(
            route = AppRoute.Login.route
        ) {

            LoginScreen(
                viewModel = viewModel(),
                onLoginSuccess = {

                    navController.navigate(
                        AppRoute.Home.route
                    )
                }
            )
        }

        composable(
            route = AppRoute.Home.route
        ) {

            HomeScreen(
                onLogout = {

                    navController.navigate(
                        AppRoute.Login.route
                    ) {
                        popUpTo(
                            AppRoute.Home.route
                        ) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}