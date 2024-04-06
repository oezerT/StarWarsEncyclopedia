package com.example.starwarsencyclopedia.data.local.planets

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PlanetDao {
    @Upsert
    suspend fun upsertAll(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planetentity")
    fun pagingSource(): PagingSource<Int, PlanetEntity>

    @Query("DELETE FROM planetentity")
    suspend fun clearAll()
}
