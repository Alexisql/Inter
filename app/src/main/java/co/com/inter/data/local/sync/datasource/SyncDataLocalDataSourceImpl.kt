package co.com.inter.data.local.sync.datasource

import co.com.inter.data.local.dao.InterDao
import co.com.inter.data.local.sync.entity.SchemeEntity
import javax.inject.Inject

class SyncDataLocalDataSourceImpl @Inject constructor(
    private val interDao: InterDao
) : ISyncDataLocalDataSource {

    override suspend fun saveScheme(scheme: List<SchemeEntity>) {
        interDao.insertScheme(scheme)
    }
}