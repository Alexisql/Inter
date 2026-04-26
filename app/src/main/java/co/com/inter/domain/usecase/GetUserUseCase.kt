package co.com.inter.domain.usecase

import co.com.inter.domain.model.User
import co.com.inter.domain.repository.IUserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(): Result<User?> = userRepository.getUser()

}