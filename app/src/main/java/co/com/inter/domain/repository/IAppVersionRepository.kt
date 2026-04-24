package co.com.inter.domain.repository

interface IAppVersionRepository {
    suspend fun getRemoteAppVersion(): Result<Int>
    fun getLocalAppVersion(): Int
}