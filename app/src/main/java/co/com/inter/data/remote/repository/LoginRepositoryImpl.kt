package co.com.inter.data.remote.repository

import co.com.inter.data.remote.dto.LoginRequestDto
import co.com.inter.data.remote.dto.toDomain
import co.com.inter.data.remote.service.InterService
import co.com.inter.domain.model.User
import co.com.inter.domain.repository.ILoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val interService: InterService,
    private val dispatcherIO: CoroutineDispatcher
) : ILoginRepository {

    override suspend fun login(): Result<User> {
        return withContext(dispatcherIO) {
            try {
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

                val response = interService.login(
                    headers = headers,
                    request = body
                )

                Result.success(response.toDomain())

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}