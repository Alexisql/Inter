package co.com.inter.domain.usecase

import co.com.inter.domain.model.CheckVersionState
import co.com.inter.domain.model.InitEvent
import co.com.inter.domain.model.InitResult
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class InitializationUseCase @Inject constructor(
    private val checkVersionUseCase: CheckAppVersionUseCase,
    private val loginUseCase: LoginUseCase,
    private val syncDataUseCase: SyncDataUseCase
) {
    suspend operator fun invoke(): InitResult = supervisorScope {
        try {
            val events = mutableListOf<InitEvent>()

            val checkVersion = async { checkVersionUseCase() }.await()
            async { loginUseCase() }.await()
            async { syncDataUseCase() }.await()

            checkVersion.onSuccess { checkVersionState ->
                when (checkVersionState) {
                    CheckVersionState.Lower -> {
                        events.add(InitEvent.LowerApp)
                    }

                    CheckVersionState.Upper -> {
                        events.add(InitEvent.UpperApp)
                    }

                    else -> Unit
                }
            }

            InitResult(events)
        } catch (exception: Exception) {
            InitResult(mutableListOf(InitEvent.Error(exception)))
        }
    }
}