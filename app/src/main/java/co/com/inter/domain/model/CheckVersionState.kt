package co.com.inter.domain.model

sealed class CheckVersionState {
    object Lower : CheckVersionState()
    object Upper : CheckVersionState()
    object Updated : CheckVersionState()
}