package co.com.inter.data.local.sync.datasource

import co.com.inter.data.local.sync.entity.SchemeEntity

interface ISchemeLocalDataSource {
    suspend fun saveScheme(scheme: List<SchemeEntity>)
}