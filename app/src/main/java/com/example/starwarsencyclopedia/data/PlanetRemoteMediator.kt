package com.example.starwarsencyclopedia.data

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwarsencyclopedia.data.local.StarWarsDatabase
import com.example.starwarsencyclopedia.data.local.planets.PlanetEntity
import com.example.starwarsencyclopedia.data.remote.StarWarsApi
import com.example.starwarsencyclopedia.data.util.toEntity

@OptIn(ExperimentalPagingApi::class)
class PlanetRemoteMediator(
    private val starWarsDb: StarWarsDatabase,
    private val starWarsApi: StarWarsApi,
) : RemoteMediator<Int, PlanetEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlanetEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItemId = state.lastItemOrNull()?.url?.let {
                        Uri.parse(it).lastPathSegment?.toInt()
                    }
                    if (lastItemId == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    } else {
                        (lastItemId / state.config.pageSize) + 1
                    }
                }
            }

            val planets = starWarsApi.getPlanets(
                page = currentPage
            )

            starWarsDb.withTransaction {
                if (loadType == LoadType.REFRESH)
                    starWarsDb.planetDao.clearAll()

                val planetEntities = planets.results.map {
                    it.toEntity()
                }
                starWarsDb.planetDao.upsertAll(planetEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = planets.next == null
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}