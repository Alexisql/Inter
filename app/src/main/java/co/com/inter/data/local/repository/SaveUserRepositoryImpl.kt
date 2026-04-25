package co.com.inter.data.local.repository

import co.com.inter.data.local.dao.InterDao
import co.com.inter.domain.model.User
import co.com.inter.domain.model.toEntity
import co.com.inter.domain.repository.ISaveUserRepository
import javax.inject.Inject

class SaveUserRepositoryImpl @Inject constructor(
    private val interDao: InterDao
) : ISaveUserRepository {

    override suspend fun saveUser(user: User) {
        interDao.insertUser(user.toEntity())
    }
}