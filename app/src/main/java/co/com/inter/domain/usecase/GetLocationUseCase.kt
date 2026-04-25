package co.com.inter.domain.usecase

import co.com.inter.domain.model.Location
import co.com.inter.domain.repository.ILocationRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository: ILocationRepository
) {
    suspend operator fun invoke(): Result<List<Location>> = repository.getLocations()
}