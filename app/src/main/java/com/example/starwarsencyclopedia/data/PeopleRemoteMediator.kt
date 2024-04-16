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

const val FIRST_PAGE_KEY = 1

@OptIn(ExperimentalPagingApi::class)
class PeopleRemoteMediator(
    private val starWarsDb: StarWarsDatabase,
    private val starWarsApi: StarWarsApi,
) : RemoteMediator<Int, PersonEntity>() {

    private val categoryDao = starWarsDb.personDao
    private val remoteKeyDao = starWarsDb.remoteKeyDao
    private val category = RemoteKeyEntity.Category.PEOPLE

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PersonEntity>
    ) = try {
        when (loadType) {
            LoadType.REFRESH -> FIRST_PAGE_KEY
            LoadType.PREPEND -> null
            LoadType.APPEND -> remoteKeyDao.remoteKeyByCategory(category)
        }?.let { currentPage ->
            val response = starWarsApi.getPeople(currentPage)
            val isLastPage = response.next == null
            val nextPage = currentPage.inc().takeUnless { isLastPage }
            val newRemoteKeyEntity = RemoteKeyEntity(category, nextPage)
            val entities = response.results.map { it.toEntity() }

            starWarsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByCategory(category)
                    categoryDao.clearAll()
                }
                remoteKeyDao.insertOrReplace(newRemoteKeyEntity)
                categoryDao.upsertAll(entities)
            }

            MediatorResult.Success(isLastPage)

        } ?: MediatorResult.Success(true)

    } catch (e: Exception) {
        MediatorResult.Error(e)
    }
}