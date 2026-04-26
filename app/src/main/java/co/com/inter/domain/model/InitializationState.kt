package co.com.inter.domain.model

sealed class InitializationState {
    object LowerApp : InitializationState()
    object UpperApp : InitializationState()
    object Success : InitializationState()
}