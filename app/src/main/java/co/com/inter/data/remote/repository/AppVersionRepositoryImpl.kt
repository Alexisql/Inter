package co.com.inter.data.remote.repository

import android.content.Context
import androidx.core.content.pm.PackageInfoCompat
import co.com.inter.data.remote.service.InterService
import co.com.inter.domain.repository.IAppVersionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppVersionRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dispatcherIO: CoroutineDispatcher,
    private val interService: InterService
) : IAppVersionRepository {

    override suspend fun getRemoteAppVersion(): Result<Int> {
        return withContext(dispatcherIO){
            try {
                val response = interService.getRemoteAppVersion()
                Result.success(response.toInt())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override fun getLocalAppVersion(): Int {
        val packageInfo = context.packageManager
            .getPackageInfo(context.packageName, 0)
        return PackageInfoCompat.getLongVersionCode(packageInfo).toInt()
    }

}