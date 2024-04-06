package com.example.starwarsencyclopedia.data.local.remoteKeys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeyEntity(
    @PrimaryKey val category: Category,
    val nextKey: Int?
) {
    enum class Category {
        FILMS,
        PEOPLE,
        PLANETS
    }
}
