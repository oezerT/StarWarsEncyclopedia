package com.example.starwarsencyclopedia.data.local.films

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FilmDao {
    @Upsert
    suspend fun upsertAll(film: List<FilmEntity>)

    @Query("SELECT * FROM filmentity")
    fun pagingSource(): PagingSource<Int, FilmEntity>

    @Query("DELETE FROM filmentity")
    suspend fun clearAll()
}
