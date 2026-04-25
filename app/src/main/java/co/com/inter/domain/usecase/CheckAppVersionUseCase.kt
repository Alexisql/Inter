package co.com.inter.domain.usecase

import co.com.inter.domain.repository.ICheckAppVersionRepository
import javax.inject.Inject

private const val APP_OLDER_VERSION = "Your app has an older version than the server."
private const val APP_HIGHER_VERSION = "Your app has a higher version than the server."

class CheckAppVersionUseCase @Inject constructor(
    private val repository: ICheckAppVersionRepository
) {
    suspend operator fun invoke(): Result<String?> {
        return runCatching {
            val remote = repository.getRemoteAppVersion().getOrThrow()
            val local = repository.getLocalAppVersion()
            val message = when {
                remote > local -> APP_OLDER_VERSION
                remote < local -> APP_HIGHER_VERSION
                else -> null
            }
            message
        }
    }
}