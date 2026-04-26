package co.com.inter.data.local.user.datasource

import co.com.inter.data.local.dao.InterDao
import co.com.inter.data.local.user.entity.UserEntity
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val interDao: InterDao
) : IUserLocalDataSource {

    override suspend fun saveUser(user: UserEntity) {
        interDao.insertUser(user)
    }

    override suspend fun getUser(): UserEntity? = interDao.getUser()

}