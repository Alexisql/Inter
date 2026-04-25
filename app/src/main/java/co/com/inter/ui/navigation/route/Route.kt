package co.com.inter.ui.navigation.route

sealed class Route(val route: String) {
    data object Splash : Route("Splash")
    data object Home : Route("Home")
    data object Table : Route("Table")
    data object Location : Route("Location")
}