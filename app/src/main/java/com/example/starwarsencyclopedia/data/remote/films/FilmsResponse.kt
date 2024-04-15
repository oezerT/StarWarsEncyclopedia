package com.example.starwarsencyclopedia.data.remote.films

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FilmsResponse(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String? = null,
    @SerialName("previous") val previous: String? = null,
    @SerialName("results") val results: List<FilmDto> = emptyList()
)
