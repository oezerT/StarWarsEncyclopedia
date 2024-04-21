package com.example.starwarsencyclopedia.data.remote.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SpeciesDto(
    @SerialName("name") val name: String,
    @SerialName("classification") val classification: String,
    @SerialName("designation") val designation: String,
    @SerialName("average_height") val averageHeight: String,
    @SerialName("skin_colors") val skinColors: String,
    @SerialName("hair_colors") val hairColors: String,
    @SerialName("eye_colors") val eyeColors: String,
    @SerialName("average_lifespan") val averageLifespan: String,
    @SerialName("homeworld") val homeworld: String?,
    @SerialName("language") val language: String,
    @SerialName("people") val people: List<String> = emptyList(),
    @SerialName("films") val films: List<String> = emptyList(),
    @SerialName("created") val created: String,
    @SerialName("edited") val edited: String,
    @SerialName("url") val url: String,
)
