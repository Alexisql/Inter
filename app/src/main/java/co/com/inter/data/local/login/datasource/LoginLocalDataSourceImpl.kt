package co.com.inter.data.local.login.datasource

import co.com.inter.data.local.dao.InterDao
import co.com.inter.data.local.login.entity.UserEntity
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(
    private val interDao: InterDao
) : ILoginLocalDataSource {

    override suspend fun saveUser(user: UserEntity) {
        interDao.insertUser(user)
    }
}