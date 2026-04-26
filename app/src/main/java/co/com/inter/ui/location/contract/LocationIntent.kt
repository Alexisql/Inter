package co.com.inter.ui.location.contract

sealed interface LocationIntent {
    object OnBack : LocationIntent
}