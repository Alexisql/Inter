package co.com.inter.data.repository

import co.com.inter.data.local.sync.datasource.ISyncDataLocalDataSource
import co.com.inter.data.remote.sync.datasource.ISyncDataRemoteDataSource
import co.com.inter.data.remote.sync.dto.toEntity
import co.com.inter.domain.repository.ISyncDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncDataRepositoryImpl @Inject constructor(
    private val local: ISyncDataLocalDataSource,
    private val remote: ISyncDataRemoteDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : ISyncDataRepository {

    override suspend fun sync(): Result<Unit> =
        withContext(dispatcherIO) {
            remote.getScheme().mapCatching { scheme ->
                local.saveScheme(scheme.map { it.toEntity() })
            }
        }
}