package com.example.starwarsencyclopedia.data.remote.films

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FilmsResponse(
    @SerialName("count") var count: Int,
    @SerialName("next") var next: String? = null,
    @SerialName("previous") var previous: String? = null,
    @SerialName("results") var results: List<FilmDto> = emptyList()
)
