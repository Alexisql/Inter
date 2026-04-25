package co.com.inter.data.remote.dto

import co.com.inter.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("Usuario") val user: String,
    @SerialName("Identificacion") val identification: String?,
    @SerialName("Nombre") val name: String?
)

fun UserResponseDto.toDomain() = User(user, identification, name)
