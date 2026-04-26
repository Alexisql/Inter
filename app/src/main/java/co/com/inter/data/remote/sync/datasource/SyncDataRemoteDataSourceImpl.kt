package co.com.inter.data.remote.sync.datasource

import co.com.inter.data.remote.interceptor.HeaderProvider
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.sync.dto.SchemeResponseDto
import co.com.inter.data.remote.util.safeApiCall
import javax.inject.Inject

private const val CLAZZ = "SchemeRepositoryImpl"

class SyncDataRemoteDataSourceImpl @Inject constructor(
    private val interService: InterService,
    private val headerProvider: HeaderProvider
) : ISyncDataRemoteDataSource {

    override suspend fun getScheme(): Result<List<SchemeResponseDto>> {
        return safeApiCall(CLAZZ) {
            interService.getScheme(headerProvider.getHeaderSync())
        }
    }

}