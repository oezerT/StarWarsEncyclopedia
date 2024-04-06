package com.example.starwarsencyclopedia.data.remote.planets

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PlanetDto(
    @SerialName("name") var name: String,
    @SerialName("rotation_period") var rotationPeriod: String,
    @SerialName("orbital_period") var orbitalPeriod: String,
    @SerialName("diameter") var diameter: String,
    @SerialName("climate") var climate: String,
    @SerialName("gravity") var gravity: String,
    @SerialName("terrain") var terrain: String,
    @SerialName("surface_water") var surfaceWater: String,
    @SerialName("population") var population: String,
    @SerialName("residents") var residents: List<String> = emptyList(),
    @SerialName("films") var films: List<String> = emptyList(),
    @SerialName("created") var created: String,
    @SerialName("edited") var edited: String,
    @SerialName("url") var url: String,
)
