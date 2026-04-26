package co.com.inter.domain.repository

import co.com.inter.domain.model.User

interface IUserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUser(): Result<User?>
}