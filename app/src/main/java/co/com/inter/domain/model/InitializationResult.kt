package co.com.inter.domain.model

data class InitializationResult(
    val events: List<InitializationEvent> = emptyList()
)

sealed class InitializationEvent {
    object LowerApp : InitializationEvent()
    object UpperApp : InitializationEvent()
    data class Error(val exception: Exception) : InitializationEvent()
}