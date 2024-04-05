package com.example.starwarsencyclopedia.data.remote.films

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FilmDto(
    @SerialName("title") var title: String,
    @SerialName("episode_id") var episodeId: Int,
    @SerialName("opening_crawl") var openingCrawl: String,
    @SerialName("director") var director: String,
    @SerialName("producer") var producer: String,
    @SerialName("release_date") var releaseDate: String,
    @SerialName("characters") var characters: List<String>,
    @SerialName("planets") var planets: List<String>,
    @SerialName("starships") var starships: List<String>,
    @SerialName("vehicles") var vehicles: List<String>,
    @SerialName("species") var species: List<String>,
    @SerialName("created") var created: String,
    @SerialName("edited") var edited: String,
    @SerialName("url") var url: String,
)
