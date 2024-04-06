package com.example.starwarsencyclopedia.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwarsencyclopedia.data.local.StarWarsDatabase
import com.example.starwarsencyclopedia.data.local.people.PersonEntity
import com.example.starwarsencyclopedia.data.local.remoteKeys.RemoteKeyEntity
import com.example.starwarsencyclopedia.data.remote.StarWarsApi
import com.example.starwarsencyclopedia.data.util.toEntity

private const val FIRST_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PeopleRemoteMediator(
    private val starWarsDb: StarWarsDatabase,
    private val starWarsApi: StarWarsApi,
) : RemoteMediator<Int, PersonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PersonEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> FIRST_PAGE_INDEX
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val remoteKey = starWarsDb.withTransaction {
                        starWarsDb.remoteKeyDao.remoteKeyByCategory(
                            category = RemoteKeyEntity.Category.PEOPLE
                        )
                    }

                    remoteKey.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
            }

            val people = starWarsApi.getPeople(
                page = currentPage
            )

            starWarsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    starWarsDb.remoteKeyDao.deleteByCategory(
                        category = RemoteKeyEntity.Category.PEOPLE
                    )
                    starWarsDb.personDao.clearAll()
                }

                starWarsDb.remoteKeyDao.insertOrReplace(
                    RemoteKeyEntity(
                        category = RemoteKeyEntity.Category.PEOPLE,
                        nextKey = currentPage.inc().takeIf { people.next != null }
                    )
                )

                val peopleEntities = people.results.map {
                    it.toEntity()
                }
                starWarsDb.personDao.upsertAll(peopleEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = people.next == null
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}