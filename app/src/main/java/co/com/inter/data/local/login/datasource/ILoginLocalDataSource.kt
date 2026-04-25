package co.com.inter.data.local.login.datasource

import co.com.inter.data.local.login.entity.UserEntity

interface ILoginLocalDataSource {
    suspend fun saveUser(user: UserEntity)
}