package com.example.starwarsencyclopedia.data.util

import com.example.starwarsencyclopedia.data.local.films.FilmEntity
import com.example.starwarsencyclopedia.data.remote.model.FilmDto
import com.example.starwarsencyclopedia.domain.model.Film

fun FilmDto.toEntity() = FilmEntity(
    url = url,
    title = title,
    episodeId = episodeId,
    openingCrawl = openingCrawl,
    director = director,
    producer = producer,
    releaseDate = releaseDate,
)

fun FilmEntity.toDomain() = Film(
    title = title,
    episodeId = episodeId,
    openingCrawl = openingCrawl,
    director = director,
    producer = producer,
    releaseDate = releaseDate
)