package com.example.starwarsencyclopedia.domain.model

data class Film(
    var title: String,
    var episodeId: Int,
    var openingCrawl: String,
    var director: String,
    var producer: String,
    var releaseDate: String,
)