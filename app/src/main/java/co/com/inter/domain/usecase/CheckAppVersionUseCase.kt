package co.com.inter.domain.usecase

import co.com.inter.domain.model.CheckVersionState
import co.com.inter.domain.repository.ICheckAppVersionRepository
import javax.inject.Inject

class CheckAppVersionUseCase @Inject constructor(
    private val repository: ICheckAppVersionRepository
) {
    suspend operator fun invoke(): Result<CheckVersionState> {
        return repository.getRemoteAppVersion().mapCatching { remote ->
            val local = repository.getLocalAppVersion()
            val message = when {
                remote > local -> CheckVersionState.Lower
                remote < local -> CheckVersionState.Upper
                else -> CheckVersionState.Updated
            }
            message
        }
    }
}