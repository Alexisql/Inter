package co.com.inter.data.remote.sync.datasource

import co.com.inter.data.remote.sync.dto.SchemeResponseDto

interface ISchemeRemoteDataSource {
    suspend fun getScheme(): Result<List<SchemeResponseDto>>
}