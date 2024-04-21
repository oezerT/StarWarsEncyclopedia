package com.example.starwarsencyclopedia.data.util

import com.example.starwarsencyclopedia.data.local.species.SpeciesEntity
import com.example.starwarsencyclopedia.data.remote.model.SpeciesDto
import com.example.starwarsencyclopedia.domain.model.Species

fun SpeciesDto.toEntity() = SpeciesEntity(
    url = url,
    name = name,
    classification = classification,
    designation = designation,
    averageHeight = averageHeight,
    skinColors = skinColors,
    hairColors = hairColors,
    eyeColors = eyeColors,
    averageLifespan = averageLifespan,
    language = language
)

fun SpeciesEntity.toDomain() = Species(
    name = name,
    classification = classification,
    designation = designation,
    averageHeight = averageHeight,
    skinColors = skinColors,
    hairColors = hairColors,
    eyeColors = eyeColors,
    averageLifespan = averageLifespan,
    language = language
)