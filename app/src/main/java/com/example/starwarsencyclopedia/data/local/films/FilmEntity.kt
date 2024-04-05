package com.example.starwarsencyclopedia.data.local.films

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(
    @PrimaryKey var url: String,
    var title: String,
    var episodeId: Int,
    var openingCrawl: String,
    var director: String,
    var producer: String,
    var releaseDate: String,
)