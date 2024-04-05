package com.example.starwarsencyclopedia.data.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PersonDto(
    @SerialName("name") var name: String,
    @SerialName("height") var height: String,
    @SerialName("mass") var mass: String,
    @SerialName("hair_color") var hairColor: String,
    @SerialName("skin_color") var skinColor: String,
    @SerialName("eye_color") var eyeColor: String,
    @SerialName("birth_year") var birthYear: String,
    @SerialName("gender") var gender: String,
    @SerialName("homeworld") var homeworld: String,
    @SerialName("films") var films: List<String> = emptyList(),
    @SerialName("species") var species: List<String> = emptyList(),
    @SerialName("vehicles") var vehicles: List<String> = emptyList(),
    @SerialName("starships") var starships: List<String> = emptyList(),
    @SerialName("created") var created: String,
    @SerialName("edited") var edited: String,
    @SerialName("url") var url: String,
)
