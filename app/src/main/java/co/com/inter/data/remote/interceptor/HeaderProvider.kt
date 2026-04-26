package co.com.inter.data.remote.interceptor

import javax.inject.Inject

class HeaderProvider @Inject constructor() {
    fun getAuthHeaders(user: String): Map<String, String> {
        return mapOf(
            "Usuario" to user,
            "Identificacion" to "987204545",
            "IdUsuario" to user,
            "IdCentroServicio" to "1295",
            "NombreCentroServicio" to "PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30# 7 45",
            "IdAplicativoOrigen" to "9"
        )
    }

    fun getHeaderSync(): Map<String, String> {
        return mapOf(
            "usuario" to "usuario"
        )
    }
}