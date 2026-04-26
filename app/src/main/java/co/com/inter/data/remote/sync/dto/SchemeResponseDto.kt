package co.com.inter.data.remote.sync.dto

import co.com.inter.domain.model.Table
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchemeResponseDto(
    @SerialName("BatchSize") val batchSize: Int,
    @SerialName("Error") val error: String?,
    @SerialName("FechaActualizacionSincro") val dateUpdated: String,
    @SerialName("Filtro") val filter: String,
    @SerialName("MetodoApp") val methodApp: String?,
    @SerialName("NombreTabla") val tableName: String,
    @SerialName("NumeroCampos") val fieldNumber: Int,
    @SerialName("Pk") val pk: String,
    @SerialName("QueryCreacion") val creationQuery: String
)

fun SchemeResponseDto.toDomain() = Table(
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