package co.com.inter.domain.usecase

import co.com.inter.domain.repository.ISyncDataRepository
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val repository: ISyncDataRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.sync()
    }
}