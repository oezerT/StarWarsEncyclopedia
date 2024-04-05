package com.example.starwarsencyclopedia.data.local.people

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonEntity(
    @PrimaryKey var url: String,
    var name: String,
    var height: String,
    var mass: String,
    var hairColor: String,
    var skinColor: String,
    var eyeColor: String,
    var birthYear: String,
    var gender: String,
)