package co.com.inter.data.repository

import android.content.Context
import androidx.core.content.pm.PackageInfoCompat
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.util.safeApiCall
import co.com.inter.domain.repository.ICheckAppVersionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val CLAZZ = "AppVersionRepositoryImpl"

class CheckAppVersionRepositoryImpl @Inject constructor(
    private val context: Context,
    private val interService: InterService,
    private val dispatcherIO: CoroutineDispatcher
) : ICheckAppVersionRepository {

    override suspend fun getRemoteAppVersion(): Result<Int> =
        withContext(dispatcherIO) {
            safeApiCall(CLAZZ) {
                interService.getRemoteAppVersion().toInt()
            }
        }

    override fun getLocalAppVersion(): Int {
        val packageInfo = context.packageManager
            .getPackageInfo(context.packageName, 0)
        return PackageInfoCompat.getLongVersionCode(packageInfo).toInt()
    }

}