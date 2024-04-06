package com.example.starwarsencyclopedia.data.local.remoteKeys

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeyEntity)

    @Query("SELECT * FROM remotekeyentity WHERE category = :category")
    suspend fun remoteKeyByCategory(category: RemoteKeyEntity.Category): RemoteKeyEntity

    @Query("DELETE FROM remotekeyentity WHERE category = :category")
    suspend fun deleteByCategory(category: RemoteKeyEntity.Category)

}
