package co.com.inter.data.local.sync.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scheme")
data class SchemeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val batchSize: Int,
    val error: String?,
    val dateUpdated: String,
    val filter: String,
    val methodApp: String?,
    val tableName: String,
    val fieldNumber: Int,
    val pk: String,
    val creationQuery: String
)