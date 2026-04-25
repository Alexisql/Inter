package co.com.inter.data.repository

import co.com.inter.data.local.login.datasource.ILoginLocalDataSource
import co.com.inter.data.remote.login.datasource.ILoginRemoteDataSource
import co.com.inter.data.remote.login.dto.toEntity
import co.com.inter.domain.repository.ILoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val local: ILoginLocalDataSource,
    private val remote: ILoginRemoteDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : ILoginRepository {

    override suspend fun login(): Result<Unit> =
        withContext(dispatcherIO) {
            remote.login().mapCatching { user ->
                local.saveUser(user.toEntity())
            }
        }
}