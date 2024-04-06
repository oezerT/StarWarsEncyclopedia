package com.example.starwarsencyclopedia.data.local.planets

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanetEntity(
    @PrimaryKey var url: String,
    var name: String,
    var rotationPeriod: String,
    var orbitalPeriod: String,
    var diameter: String,
    var climate: String,
    var gravity: String,
    var terrain: String,
    var surfaceWater: String,
    var population: String,
)