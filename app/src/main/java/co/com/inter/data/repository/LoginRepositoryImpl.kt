package co.com.inter.data.repository

import co.com.inter.data.local.login.datasource.ILoginLocalDataSource
import co.com.inter.data.remote.login.datasource.ILoginRemoteDataSource
import co.com.inter.data.remote.login.dto.toEntity
import co.com.inter.domain.repository.ILoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val local: ILoginLocalDataSource,
    private val remote: ILoginRemoteDataSource
) : ILoginRepository {

    override suspend fun login(): Result<Unit> {
        val response = remote.login()
        return response.mapCatching { user ->
            local.saveUser(user.toEntity())
        }
    }
}