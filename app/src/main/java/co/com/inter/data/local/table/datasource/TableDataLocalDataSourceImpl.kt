package co.com.inter.data.local.table.datasource

import androidx.paging.PagingSource
import co.com.inter.data.local.dao.InterDao
import co.com.inter.data.local.table.entity.TableEntity
import javax.inject.Inject

class TableDataLocalDataSourceImpl @Inject constructor(
    private val interDao: InterDao
) : ITableDataLocalDataSource {

    override suspend fun saveTable(scheme: List<TableEntity>) {
        interDao.insertTable(scheme)
    }

    override fun getTable(): PagingSource<Int, TableEntity> = interDao.getAllScheme()
}