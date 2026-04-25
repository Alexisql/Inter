package co.com.inter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.com.inter.ui.home.HomeScreen
import co.com.inter.ui.navigation.route.Route
import co.com.inter.ui.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Splash.route
    ) {
        composable(Route.Splash.route) {
            SplashScreen(
                navController = navController
            )
        }
        composable(Route.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
    }
}
