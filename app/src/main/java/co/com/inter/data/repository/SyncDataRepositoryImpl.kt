package co.com.inter.data.repository

import co.com.inter.data.remote.sync.datasource.ISyncDataRemoteDataSource
import co.com.inter.data.remote.sync.dto.toDomain
import co.com.inter.domain.model.Table
import co.com.inter.domain.repository.ISyncDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncDataRepositoryImpl @Inject constructor(
    private val remote: ISyncDataRemoteDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : ISyncDataRepository {

    override suspend fun sync(): Result<List<Table>> =
        withContext(dispatcherIO) {
            remote.getScheme().mapCatching { scheme ->
                scheme.map { it.toDomain() }
            }
        }
}