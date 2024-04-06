package com.example.starwarsencyclopedia.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwarsencyclopedia.data.local.films.FilmEntity
import com.example.starwarsencyclopedia.data.local.StarWarsDatabase
import com.example.starwarsencyclopedia.data.local.remoteKeys.RemoteKeyEntity
import com.example.starwarsencyclopedia.data.remote.StarWarsApi
import com.example.starwarsencyclopedia.data.util.toEntity

@OptIn(ExperimentalPagingApi::class)
class FilmRemoteMediator(
    private val starWarsDb: StarWarsDatabase,
    private val starWarsApi: StarWarsApi,
) : RemoteMediator<Int, FilmEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FilmEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val remoteKey = starWarsDb.withTransaction {
                        starWarsDb.remoteKeyDao.remoteKeyByCategory(
                            category = RemoteKeyEntity.Category.FILMS
                        )
                    }

                    remoteKey.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
            }

            val films = starWarsApi.getFilms(
                page = currentPage
            )

            starWarsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    starWarsDb.remoteKeyDao.deleteByCategory(
                        category = RemoteKeyEntity.Category.FILMS
                    )
                    starWarsDb.filmDao.clearAll()
                }

                starWarsDb.remoteKeyDao.insertOrReplace(
                    RemoteKeyEntity(
                        category = RemoteKeyEntity.Category.FILMS,
                        nextKey = currentPage.inc().takeIf { films.next != null }
                    )
                )

                val peopleEntities = films.results.map {
                    it.toEntity()
                }
                starWarsDb.filmDao.upsertAll(peopleEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = films.next == null
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}