package com.example.starwarsencyclopedia.domain.model

data class Film(
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
)