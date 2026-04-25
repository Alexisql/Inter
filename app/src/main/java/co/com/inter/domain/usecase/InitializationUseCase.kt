package co.com.inter.domain.usecase

import co.com.inter.domain.model.CheckVersionState
import co.com.inter.domain.model.InitializationEvent
import co.com.inter.domain.model.InitializationResult
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class InitializationUseCase @Inject constructor(
    private val checkVersionUseCase: CheckAppVersionUseCase,
    private val loginUseCase: LoginUseCase,
    private val syncDataUseCase: SyncDataUseCase
) {
    suspend operator fun invoke(): InitializationResult = supervisorScope {
        try {
            val events = mutableListOf<InitializationEvent>()

            val checkVersion = async { checkVersionUseCase() }.await()
            async { loginUseCase() }.await()
            async { syncDataUseCase() }.await()

            checkVersion.onSuccess { checkVersionState ->
                when (checkVersionState) {
                    CheckVersionState.Lower -> {
                        events.add(InitializationEvent.LowerApp)
                    }

                    CheckVersionState.Upper -> {
                        events.add(InitializationEvent.UpperApp)
                    }

                    else -> Unit
                }
            }

            InitializationResult(events)
        } catch (exception: Exception) {
            InitializationResult(mutableListOf(InitializationEvent.Error(exception)))
        }
    }
}