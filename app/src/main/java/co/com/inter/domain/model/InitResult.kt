package co.com.inter.domain.model

data class InitResult(
    val events: List<InitEvent> = emptyList()
)

sealed class InitEvent {
    object LowerApp : InitEvent()
    object UpperApp : InitEvent()
    data class Error(val exception: Exception) : InitEvent()
}