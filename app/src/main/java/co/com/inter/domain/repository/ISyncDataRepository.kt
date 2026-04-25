package co.com.inter.domain.repository

interface ISyncDataRepository {
    suspend fun sync(): Result<Unit>
}