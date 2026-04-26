package co.com.inter.ui.home.contract

sealed interface HomeIntent {
    object NavigateToTables : HomeIntent
    object NavigateToLocations : HomeIntent
}