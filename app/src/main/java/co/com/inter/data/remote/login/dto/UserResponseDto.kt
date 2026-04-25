package co.com.inter.data.remote.login.dto

import co.com.inter.data.local.login.entity.UserEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("Usuario") val user: String,
    @SerialName("Identificacion") val identification: String?,
    @SerialName("Nombre") val name: String?
)

fun UserResponseDto.toEntity() = UserEntity(
    id = identification,
    user = user,
    name = name
)
