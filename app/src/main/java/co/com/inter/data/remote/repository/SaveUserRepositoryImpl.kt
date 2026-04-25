package co.com.inter.data.remote.repository

import co.com.inter.domain.model.User
import co.com.inter.domain.repository.ISaveUserRepository
import javax.inject.Inject

class SaveUserRepositoryImpl @Inject constructor(
) : ISaveUserRepository {

    override suspend fun saveUser(user: User) {
        //
    }
}