package co.com.inter.domain.usecase

import co.com.inter.domain.model.CheckVersionState
import co.com.inter.domain.model.InitializationState
import javax.inject.Inject

class InitializationUseCase @Inject constructor(
    private val checkVersionUseCase: CheckAppVersionUseCase,
    private val loginUseCase: LoginUseCase,
    private val syncDataUseCase: SyncDataUseCase
) {
    suspend operator fun invoke(): Result<InitializationState> {
        return checkVersionUseCase().fold(
            onSuccess = { version ->
                when (version) {
                    CheckVersionState.Lower -> Result.success(InitializationState.LowerApp)
                    CheckVersionState.Upper -> Result.success(InitializationState.UpperApp)
                    CheckVersionState.Updated -> {
                        loginUseCase().fold(
                            onSuccess = {
                                syncDataUseCase().map {
                                    InitializationState.Success
                                }
                            },
                            onFailure = {
                                Result.failure(it)
                            }
                        )
                    }
                }
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
}