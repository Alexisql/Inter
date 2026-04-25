package co.com.inter.domain.usecase

import co.com.inter.domain.repository.ILoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: ILoginRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.login()
    }
}