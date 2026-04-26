package co.com.inter.domain.repository

import co.com.inter.domain.model.Location

interface ILocationRepository {
    suspend fun getLocations(): Result<List<Location>>
}