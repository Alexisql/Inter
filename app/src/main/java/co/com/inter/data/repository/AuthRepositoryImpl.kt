package co.com.inter.data.repository

import co.com.inter.data.remote.auth.datasource.IAuthRemoteDataSource
import co.com.inter.data.remote.auth.dto.toDomain
import co.com.inter.domain.model.User
import co.com.inter.domain.repository.IAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: IAuthRemoteDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : IAuthRepository {

    override suspend fun login(): Result<User> =
        withContext(dispatcherIO) {
            remote.login().mapCatching { user ->
                user.toDomain()
            }
        }
}