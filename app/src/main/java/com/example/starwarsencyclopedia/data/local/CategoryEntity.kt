package com.example.starwarsencyclopedia.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
sealed class CategoryEntity(
    @PrimaryKey open var url: String,
) {

    data class PlanetEntity(
        @PrimaryKey override var url: String,
        var name: String,
        var rotationPeriod: String,
        var orbitalPeriod: String,
        var diameter: String,
        var climate: String,
        var gravity: String,
        var terrain: String,
        var surfaceWater: String,
        var population: String,
    ) : CategoryEntity(url)
}