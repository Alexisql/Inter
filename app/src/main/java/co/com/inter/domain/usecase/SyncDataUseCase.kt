package co.com.inter.domain.usecase

import co.com.inter.domain.repository.ITableRepository
import co.com.inter.domain.repository.ISyncDataRepository
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val syncRepository: ISyncDataRepository,
    private val tableRepository: ITableRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return syncRepository.sync().mapCatching { scheme ->
            tableRepository.saveTable(scheme)
        }
    }
}