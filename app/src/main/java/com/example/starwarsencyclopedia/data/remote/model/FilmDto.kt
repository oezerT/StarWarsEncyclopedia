package com.example.starwarsencyclopedia.data.remote.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FilmDto(
    @SerialName("title") val title: String,
    @SerialName("episode_id") val episodeId: Int,
    @SerialName("opening_crawl") val openingCrawl: String,
    @SerialName("director") val director: String,
    @SerialName("producer") val producer: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("characters") val characters: List<String>,
    @SerialName("planets") val planets: List<String>,
    @SerialName("starships") val starships: List<String>,
    @SerialName("vehicles") val vehicles: List<String>,
    @SerialName("species") val species: List<String>,
    @SerialName("created") val created: String,
    @SerialName("edited") val edited: String,
    @SerialName("url") val url: String,
)
