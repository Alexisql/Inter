package co.com.inter.domain.model

import co.com.inter.data.local.entity.UserEntity

data class User(
    val id: String?,
    val user: String,
    val name: String?
)

fun User.toEntity() = UserEntity(
    id = id,
    user = user,
    name = name,
)