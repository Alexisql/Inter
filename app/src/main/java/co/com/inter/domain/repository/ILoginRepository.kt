package co.com.inter.domain.repository

import co.com.inter.domain.model.User

interface ILoginRepository {
    suspend fun login(): Result<User>
}