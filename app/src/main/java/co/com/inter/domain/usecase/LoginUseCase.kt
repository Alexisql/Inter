package co.com.inter.domain.usecase

import co.com.inter.domain.repository.ILoginRepository
import co.com.inter.domain.repository.ISaveUserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: ILoginRepository,
    private val saveUserRepository: ISaveUserRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        return loginRepository.login().fold(
            onSuccess = { response ->
                saveUserRepository.saveUser(response)
                Result.success(Unit)
            },
            onFailure = { Result.failure(it) }
        )
    }
}