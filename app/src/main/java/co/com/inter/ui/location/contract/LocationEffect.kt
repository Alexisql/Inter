package co.com.inter.ui.location.contract

sealed interface LocationEffect {
    object OnBack : LocationEffect
    data class ShowError(val message: String?) : LocationEffect
}