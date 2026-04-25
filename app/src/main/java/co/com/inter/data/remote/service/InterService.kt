package co.com.inter.data.remote.service

import co.com.inter.data.remote.dto.LoginRequestDto
import co.com.inter.data.remote.dto.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface InterService {

    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun getRemoteAppVersion(): String

    @POST("FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp")
    suspend fun login(
        @HeaderMap headers: Map<String, String>,
        @Body request: LoginRequestDto
    ): UserResponseDto

}