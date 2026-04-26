package co.com.inter.data.remote.location.datasource

import co.com.inter.data.remote.location.dto.LocationRemoteDto

interface ILocationRemoteDataSource {
    suspend fun getLocations(): Result<List<LocationRemoteDto>>
}