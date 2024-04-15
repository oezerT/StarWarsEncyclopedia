package com.example.starwarsencyclopedia.data.local.planets

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanetEntity(
    @PrimaryKey val url: String,
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: String,
    val population: String,
)