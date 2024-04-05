package com.example.starwarsencyclopedia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersonEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract val personDao: PersonDao
}