package co.com.inter.domain.repository

import co.com.inter.domain.model.User

interface ISaveUserRepository {
    suspend fun saveUser(user: User)
}