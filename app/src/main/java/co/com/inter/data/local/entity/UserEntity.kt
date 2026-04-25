package co.com.inter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val uuid: Long = 0,
    val id: String?,
    val user: String,
    val name: String?
)
