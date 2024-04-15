package com.example.starwarsencyclopedia.data.remote.planets

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PlanetsResponse(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String? = null,
    @SerialName("previous") val previous: String? = null,
    @SerialName("results") val results: List<PlanetDto> = emptyList()
)
