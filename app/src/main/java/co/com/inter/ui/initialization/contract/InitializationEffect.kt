package co.com.inter.ui.initialization.contract

sealed interface InitializationEffect {
    data class ShowError(val message: String?) : InitializationEffect
    object OnNavigate : InitializationEffect
}