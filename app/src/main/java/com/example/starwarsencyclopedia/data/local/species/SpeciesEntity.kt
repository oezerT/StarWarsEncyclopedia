package com.example.starwarsencyclopedia.data.local.species

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpeciesEntity(
    @PrimaryKey val url: String,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val skinColors: String,
    val hairColors: String,
    val eyeColors: String,
    val averageLifespan: String,
    val language: String,
)