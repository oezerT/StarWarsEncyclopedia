package com.example.starwarsencyclopedia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarsencyclopedia.data.local.films.FilmDao
import com.example.starwarsencyclopedia.data.local.films.FilmEntity
import com.example.starwarsencyclopedia.data.local.people.PersonDao
import com.example.starwarsencyclopedia.data.local.people.PersonEntity
import com.example.starwarsencyclopedia.data.local.planets.PlanetDao
import com.example.starwarsencyclopedia.data.local.planets.PlanetEntity

@Database(
    entities = [PersonEntity::class, FilmEntity::class, PlanetEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract val personDao: PersonDao

    abstract val filmDao: FilmDao

    abstract val planetDao: PlanetDao
}