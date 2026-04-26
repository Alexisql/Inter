package co.com.inter.data.local.table.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.com.inter.domain.model.Table

@Entity(tableName = "scheme")
data class TableEntity(
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

fun TableEntity.toDomain() = Table(
    batchSize = batchSize,
    error = error,
    dateUpdated = dateUpdated,
    filter = filter,
    methodApp = methodApp,
    tableName = tableName,
    fieldNumber = fieldNumber,
    pk = pk,
    creationQuery = creationQuery
)