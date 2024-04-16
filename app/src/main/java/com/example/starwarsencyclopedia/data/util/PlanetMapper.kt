package com.example.starwarsencyclopedia.data.util

import com.example.starwarsencyclopedia.data.local.planets.PlanetEntity
import com.example.starwarsencyclopedia.data.remote.model.PlanetDto
import com.example.starwarsencyclopedia.domain.model.Planet

fun PlanetDto.toEntity() = PlanetEntity(
    url = url,
    name = name,
    rotationPeriod = rotationPeriod,
    orbitalPeriod = orbitalPeriod,
    diameter = diameter,
    climate = climate,
    gravity = gravity,
    terrain = terrain,
    surfaceWater = surfaceWater,
    population = population
)

fun PlanetEntity.toDomain() = Planet(
    name = name,
    rotationPeriod = rotationPeriod,
    orbitalPeriod = orbitalPeriod,
    diameter = diameter,
    climate = climate,
    gravity = gravity,
    terrain = terrain,
    surfaceWater = surfaceWater,
    population = population
)