package com.example.starwarsencyclopedia.data.remote.planets

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PlanetDto(
    @SerialName("name") val name: String,
    @SerialName("rotation_period") val rotationPeriod: String,
    @SerialName("orbital_period") val orbitalPeriod: String,
    @SerialName("diameter") val diameter: String,
    @SerialName("climate") val climate: String,
    @SerialName("gravity") val gravity: String,
    @SerialName("terrain") val terrain: String,
    @SerialName("surface_water") val surfaceWater: String,
    @SerialName("population") val population: String,
    @SerialName("residents") val residents: List<String> = emptyList(),
    @SerialName("films") val films: List<String> = emptyList(),
    @SerialName("created") val created: String,
    @SerialName("edited") val edited: String,
    @SerialName("url") val url: String,
)
