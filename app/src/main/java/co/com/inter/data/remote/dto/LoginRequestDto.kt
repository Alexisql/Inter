package co.com.inter.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("Mac") val mac: String,
    @SerialName("NomAplicacion") val application: String,
    @SerialName("Usuario") val user: String,
    @SerialName("Password") val password: String,
    @SerialName("Path") val path: String,
)
