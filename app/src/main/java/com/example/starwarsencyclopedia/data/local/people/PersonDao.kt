package com.example.starwarsencyclopedia.data.local.people

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PersonDao {
    @Upsert
    suspend fun upsertAll(people: List<PersonEntity>)

    @Query("SELECT * FROM personentity")
    fun pagingSource(): PagingSource<Int, PersonEntity>

    @Query("DELETE FROM personentity")
    suspend fun clearAll()
}
