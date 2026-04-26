package co.com.inter.domain.repository

import co.com.inter.domain.model.User

interface IAuthRepository {
    suspend fun login(): Result<User>
}