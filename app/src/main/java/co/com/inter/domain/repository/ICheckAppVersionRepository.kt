package co.com.inter.domain.repository

interface ICheckAppVersionRepository {
    suspend fun getRemoteAppVersion(): Result<Int>
    fun getLocalAppVersion(): Int
}