package co.com.inter.data.repository

import co.com.inter.data.local.sync.datasource.ISyncDataLocalDataSource
import co.com.inter.data.remote.sync.datasource.ISyncDataRemoteDataSource
import co.com.inter.data.remote.sync.dto.toEntity
import co.com.inter.domain.repository.ISyncDataRepository
import javax.inject.Inject

class SyncDataRepositoryImpl @Inject constructor(
    private val local: ISyncDataLocalDataSource,
    private val remote: ISyncDataRemoteDataSource
) : ISyncDataRepository {

    override suspend fun sync(): Result<Unit> {
        val response = remote.getScheme()
        return response.mapCatching { scheme ->
            local.saveScheme(scheme.map { it.toEntity() })
        }
    }
}