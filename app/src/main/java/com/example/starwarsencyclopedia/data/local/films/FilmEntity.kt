package com.example.starwarsencyclopedia.data.local.films

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(
    @PrimaryKey val url: String,
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
)