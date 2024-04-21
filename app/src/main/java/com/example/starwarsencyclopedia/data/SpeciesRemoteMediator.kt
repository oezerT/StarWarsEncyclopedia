package com.example.starwarsencyclopedia.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwarsencyclopedia.data.local.StarWarsDatabase
import com.example.starwarsencyclopedia.data.local.remoteKeys.RemoteKeyEntity
import com.example.starwarsencyclopedia.data.local.species.SpeciesEntity
import com.example.starwarsencyclopedia.data.remote.StarWarsApi
import com.example.starwarsencyclopedia.data.util.toEntity

@OptIn(ExperimentalPagingApi::class)
class SpeciesRemoteMediator(
    private val starWarsDb: StarWarsDatabase,
    private val starWarsApi: StarWarsApi,
) : RemoteMediator<Int, SpeciesEntity>() {

    private val categoryDao = starWarsDb.speciesDao
    private val remoteKeyDao = starWarsDb.remoteKeyDao
    private val category = RemoteKeyEntity.Category.SPECIES

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SpeciesEntity>
    ) = try {
        when (loadType) {
            LoadType.REFRESH -> FIRST_PAGE_KEY
            LoadType.PREPEND -> null
            LoadType.APPEND -> remoteKeyDao.remoteKeyByCategory(category)
        }?.let { currentPage ->
            val response = starWarsApi.getSpecies(currentPage)
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