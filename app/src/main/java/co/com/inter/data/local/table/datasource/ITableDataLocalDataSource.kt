package co.com.inter.data.local.table.datasource

import androidx.paging.PagingSource
import co.com.inter.data.local.table.entity.TableEntity

interface ITableDataLocalDataSource {
    suspend fun saveTable(scheme: List<TableEntity>)
    fun getTable(): PagingSource<Int, TableEntity>
}