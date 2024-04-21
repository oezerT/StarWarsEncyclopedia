package com.example.starwarsencyclopedia.data.local.species

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SpeciesDao {
    @Upsert
    suspend fun upsertAll(planets: List<SpeciesEntity>)

    @Query("SELECT * FROM speciesentity")
    fun pagingSource(): PagingSource<Int, SpeciesEntity>

    @Query("DELETE FROM speciesentity")
    suspend fun clearAll()
}
