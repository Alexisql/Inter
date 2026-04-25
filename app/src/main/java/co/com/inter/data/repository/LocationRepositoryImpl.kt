package co.com.inter.data.repository

import co.com.inter.data.remote.location.datasource.ILocationRemoteDataSource
import co.com.inter.data.remote.location.dto.toDomain
import co.com.inter.domain.model.Location
import co.com.inter.domain.repository.ILocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remote: ILocationRemoteDataSource,
) : ILocationRepository {
    override suspend fun getLocations(): Result<List<Location>> {
        val response = remote.getLocations()
        return response.mapCatching { locations ->
            locations.map { it.toDomain() }
        }
    }

}