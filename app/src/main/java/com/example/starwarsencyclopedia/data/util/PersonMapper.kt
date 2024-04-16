package com.example.starwarsencyclopedia.data.util

import com.example.starwarsencyclopedia.data.local.people.PersonEntity
import com.example.starwarsencyclopedia.data.remote.model.PersonDto
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
)

fun PersonEntity.toDomain() = Person(
    name = name,
    height = height,
    mass = mass,
    hairColor = hairColor,
    skinColor = skinColor,
    eyeColor = eyeColor,
    birthYear = birthYear,
    gender = gender,
)