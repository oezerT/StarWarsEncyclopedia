package com.example.starwarsencyclopedia.data

import com.example.starwarsencyclopedia.data.local.PersonEntity
import com.example.starwarsencyclopedia.data.remote.PersonDto
import com.example.starwarsencyclopedia.domain.model.Person

fun PersonDto.toEntity() = PersonEntity(
    url = url,
    name = name,
    height = height,
    mass = mass,
    hairColor = hairColor,
    skinColor = skinColor,
    eyeColor = eyeColor,
    birthYear = birthYear,
    gender = gender,
    homeworld = homeworld,
    created = created,
    edited = edited
)

fun PersonEntity.toDomain() = Person(
    url = url,
    name = name,
    height = height,
    mass = mass,
    hairColor = hairColor,
    skinColor = skinColor,
    eyeColor = eyeColor,
    birthYear = birthYear,
    gender = gender,
    homeworld = homeworld,
)