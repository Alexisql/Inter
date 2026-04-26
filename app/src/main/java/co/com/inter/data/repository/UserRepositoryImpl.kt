package co.com.inter.data.repository

import co.com.inter.data.local.user.datasource.IUserLocalDataSource
import co.com.inter.data.local.user.entity.toDomain
import co.com.inter.domain.model.User
import co.com.inter.domain.model.toEntity
import co.com.inter.domain.repository.IUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val local: IUserLocalDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : IUserRepository {
    override suspend fun saveUser(user: User) {
        withContext(dispatcherIO) {
            local.saveUser(user.toEntity())
        }
    }

    override suspend fun getUser(): Result<User?> =
        withContext(dispatcherIO) {
            runCatching {
                local.getUser()?.toDomain()
            }
        }
}