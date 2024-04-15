package com.example.starwarsencyclopedia.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
sealed class CategoryEntity(
    @PrimaryKey open val url: String,
) {

    data class PlanetEntity(
        @PrimaryKey override val url: String,
        val name: String,
        val rotationPeriod: String,
        val orbitalPeriod: String,
        val diameter: String,
        val climate: String,
        val gravity: String,
        val terrain: String,
        val surfaceWater: String,
        val population: String,
    ) : CategoryEntity(url)
}