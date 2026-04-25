package co.com.inter.data.remote.location.datasource

import co.com.inter.data.remote.location.dto.LocationRemoteDto
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.util.safeApiCall
import javax.inject.Inject

private const val CLAZZ = "LocationRemoteDataSourceImpl"

class LocationRemoteDataSourceImpl @Inject constructor(
    private val interService: InterService
) : ILocationRemoteDataSource {
    override suspend fun getLocations(): Result<List<LocationRemoteDto>> {
        return safeApiCall(CLAZZ) {
            interService.getLocations()
        }
    }
}