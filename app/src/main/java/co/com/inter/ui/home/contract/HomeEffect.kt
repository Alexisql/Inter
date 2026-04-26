package co.com.inter.ui.home.contract

sealed interface HomeEffect {
    object OnNavigate : HomeEffect
    data class ShowError(val message: String?) : HomeEffect
}