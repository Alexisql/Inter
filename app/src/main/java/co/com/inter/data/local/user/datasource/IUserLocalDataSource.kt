package co.com.inter.data.local.user.datasource

import co.com.inter.data.local.user.entity.UserEntity

interface IUserLocalDataSource {
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(): UserEntity?
}