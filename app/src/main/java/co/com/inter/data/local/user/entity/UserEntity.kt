package co.com.inter.data.local.user.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.com.inter.domain.model.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val uuid: Long = 0,
    val id: String?,
    val user: String,
    val name: String?
)

fun UserEntity.toDomain() = User(
    id = id,
    user = user,
    name = name
)