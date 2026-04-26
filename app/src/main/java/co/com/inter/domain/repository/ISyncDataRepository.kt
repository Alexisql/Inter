package co.com.inter.domain.repository

import co.com.inter.domain.model.Table

interface ISyncDataRepository {
    suspend fun sync(): Result<List<Table>>
}