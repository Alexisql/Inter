package co.com.inter.data.remote.login.datasource

import co.com.inter.data.remote.login.dto.LoginRequestDto
import co.com.inter.data.remote.login.dto.UserResponseDto
import co.com.inter.data.remote.service.InterService
import co.com.inter.data.remote.util.safeApiCall
import javax.inject.Inject

private const val CLAZZ = "LoginRepositoryImpl"

class LoginRemoteDataSourceImpl @Inject constructor(
    private val interService: InterService
) : ILoginRemoteDataSource {

    override suspend fun login(): Result<UserResponseDto> {
        val user = "pam.meredy21"

        val headers = mapOf(
            "Usuario" to user,
            "Identificacion" to "987204545",
            "IdUsuario" to user,
            "IdCentroServicio" to "1295",
            "NombreCentroServicio" to "PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30# 7 45",
            "IdAplicativoOrigen" to "9"
        )

        val body = LoginRequestDto(
            mac = "",
            application = "Controller APP",
            user = "cGFtLm1lcmVkeTIx",
            password = "SW50ZXIyMDIx",
            path = ""
        )

        return safeApiCall(CLAZZ) {
            interService.login(
                headers = headers,
                request = body
            )
        }
    }
}