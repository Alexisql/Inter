package co.com.inter.data.remote.service

import retrofit2.http.GET

interface InterService {

    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun getRemoteAppVersion(): String

}