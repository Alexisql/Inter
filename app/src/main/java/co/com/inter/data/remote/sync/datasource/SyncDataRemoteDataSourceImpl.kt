package co.com.inter.data.remote.sync.datasource

import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.sync.dto.SchemeResponseDto
import co.com.inter.data.remote.util.safeApiCall
import javax.inject.Inject

private const val CLAZZ = "SchemeRepositoryImpl"

class SyncDataRemoteDataSourceImpl @Inject constructor(
    private val interService: InterService
) : ISyncDataRemoteDataSource {

    override suspend fun getScheme(): Result<List<SchemeResponseDto>> {
        val headers = mapOf(
            "usuario" to "usuario"
        )
        return safeApiCall(CLAZZ) {
            interService.getScheme(headers)
        }
    }

}