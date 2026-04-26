package co.com.inter.domain.usecase

import co.com.inter.domain.repository.IAuthRepository
import co.com.inter.domain.repository.IUserRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: IAuthRepository,
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.login().mapCatching { user ->
            userRepository.saveUser(user)
        }
    }
}