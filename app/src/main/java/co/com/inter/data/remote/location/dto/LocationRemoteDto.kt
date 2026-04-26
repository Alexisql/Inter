package co.com.inter.data.remote.location.dto

import co.com.inter.domain.model.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationRemoteDto(
    @SerialName("AbreviacionCiudad") val abbreviationCity: String,
    @SerialName("Nombre") val name: String,
    @SerialName("NombreCompleto") val fullName: String,
    @SerialName("NombreCorto") val shortName: String
)

fun LocationRemoteDto.toDomain() = Location(
    abbreviationCity = abbreviationCity,
    name = name,
    fullName = fullName,
    shortName = shortName
)