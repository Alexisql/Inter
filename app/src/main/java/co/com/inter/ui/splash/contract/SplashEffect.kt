package co.com.inter.ui.splash.contract

sealed interface SplashEffect {
    data class ShowError(val message: String?) : SplashEffect
    object OnNavigate : SplashEffect
}