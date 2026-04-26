package co.com.inter.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import co.com.inter.data.local.table.datasource.ITableDataLocalDataSource
import co.com.inter.data.local.table.entity.toDomain
import co.com.inter.domain.model.Table
import co.com.inter.domain.model.toEntity
import co.com.inter.domain.repository.ITableRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TableRepositoryImpl @Inject constructor(
    private val local: ITableDataLocalDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : ITableRepository {
    override suspend fun saveTable(table: List<Table>) {
        withContext(dispatcherIO) {
            local.saveTable(table.map { it.toEntity() })
        }
    }

    override fun getTable(): Flow<PagingData<Table>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { local.getTable() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

}