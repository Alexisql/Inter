package co.com.inter.data.remote.service

import co.com.inter.data.remote.login.dto.LoginRequestDto
import co.com.inter.data.remote.sync.dto.SchemeResponseDto
import co.com.inter.data.remote.login.dto.UserResponseDto
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

    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    suspend fun getScheme(
        @HeaderMap headers: Map<String, String>
    ): List<SchemeResponseDto>

}