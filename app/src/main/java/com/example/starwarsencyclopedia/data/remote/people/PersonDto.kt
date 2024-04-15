package com.example.starwarsencyclopedia.data.remote.people

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PersonDto(
    @SerialName("name") val name: String,
    @SerialName("height") val height: String,
    @SerialName("mass") val mass: String,
    @SerialName("hair_color") val hairColor: String,
    @SerialName("skin_color") val skinColor: String,
    @SerialName("eye_color") val eyeColor: String,
    @SerialName("birth_year") val birthYear: String,
    @SerialName("gender") val gender: String,
    @SerialName("homeworld") val homeworld: String,
    @SerialName("films") val films: List<String> = emptyList(),
    @SerialName("species") val species: List<String> = emptyList(),
    @SerialName("vehicles") val vehicles: List<String> = emptyList(),
    @SerialName("starships") val starships: List<String> = emptyList(),
    @SerialName("created") val created: String,
    @SerialName("edited") val edited: String,
    @SerialName("url") val url: String,
)
