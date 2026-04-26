package co.com.inter.domain.repository

import androidx.paging.PagingData
import co.com.inter.domain.model.Table
import kotlinx.coroutines.flow.Flow

interface ITableRepository {
    suspend fun saveTable(scheme: List<Table>)
    fun getTable(): Flow<PagingData<Table>>
}