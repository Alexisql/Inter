package co.com.inter.data.remote.auth.datasource

import co.com.inter.data.remote.auth.dto.LoginRequestDto
import co.com.inter.data.remote.auth.dto.UserResponseDto
import co.com.inter.data.remote.interceptor.HeaderProvider
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.util.safeApiCall
import javax.inject.Inject

private const val CLAZZ = "LoginRepositoryImpl"

class AuthRemoteDataSourceImpl @Inject constructor(
    private val interService: InterService,
    private val headerProvider: HeaderProvider
) : IAuthRemoteDataSource {

    override suspend fun login(): Result<UserResponseDto> {
        val user = "pam.meredy21"

        val body = LoginRequestDto(
            mac = "",
            application = "Controller APP",
            user = "cGFtLm1lcmVkeTIx",
            password = "SW50ZXIyMDIx",
            path = ""
        )

        return safeApiCall(CLAZZ) {
            interService.login(
                headers = headerProvider.getAuthHeaders(user),
                request = body
            )
        }
    }
}