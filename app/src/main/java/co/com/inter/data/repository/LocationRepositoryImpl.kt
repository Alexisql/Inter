package co.com.inter.data.repository

import co.com.inter.data.remote.location.datasource.ILocationRemoteDataSource
import co.com.inter.data.remote.location.dto.toDomain
import co.com.inter.domain.model.Location
import co.com.inter.domain.repository.ILocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val remote: ILocationRemoteDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : ILocationRepository {
    override suspend fun getLocations(): Result<List<Location>> =
        withContext(dispatcherIO) {
            remote.getLocations().mapCatching { locations ->
                locations.map { it.toDomain() }
            }
        }
}