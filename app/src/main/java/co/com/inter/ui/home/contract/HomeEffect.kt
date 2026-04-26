package co.com.inter.ui.home.contract

sealed interface HomeEffect {
    object NavigateToTables : HomeEffect
    object NavigateToLocations : HomeEffect
    data class ShowError(val message: String?) : HomeEffect
}