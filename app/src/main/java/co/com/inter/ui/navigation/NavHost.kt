package co.com.inter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.com.inter.ui.home.HomeScreen
import co.com.inter.ui.location.LocationScreen
import co.com.inter.ui.navigation.route.Route
import co.com.inter.ui.initialization.InitializationScreen
import co.com.inter.ui.table.TableScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Initialization.route
    ) {
        composable(Route.Initialization.route) {
            InitializationScreen(
                navController = navController
            )
        }
        composable(Route.Home.route) {
            HomeScreen(
                navController = navController
            )
        }

        composable(Route.Table.route) {
            TableScreen(
                navController = navController
            )
        }

        composable(Route.Location.route) {
            LocationScreen(
                navController = navController
            )
        }
    }
}
