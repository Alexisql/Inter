package co.com.inter.domain.usecase

import co.com.inter.domain.repository.IAppVersionRepository
import javax.inject.Inject

private const val APP_OLDER_VERSION = "Your app has an older version than the server."
private const val APP_HIGHER_VERSION = "Your app has a higher version than the server."

class CheckAppVersionUseCase @Inject constructor(
    private val repository: IAppVersionRepository
) {
    suspend operator fun invoke(): Result<String?> {
        return repository.getRemoteAppVersion().fold(
            onSuccess = { remote ->
                val local = repository.getLocalAppVersion()
                val message = when {
                    remote > local -> APP_OLDER_VERSION
                    remote < local -> APP_HIGHER_VERSION
                    else -> null
                }
                Result.success(message)
            },
            onFailure = { Result.failure(it) }
        )
    }
}