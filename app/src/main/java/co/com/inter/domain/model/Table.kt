package co.com.inter.domain.model

import co.com.inter.data.local.table.entity.TableEntity

data class Table(
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

fun Table.toEntity() = TableEntity(
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