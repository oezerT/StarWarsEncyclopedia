package com.example.starwarsencyclopedia.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.starwarsencyclopedia.data.FilmRemoteMediator
import com.example.starwarsencyclopedia.data.PeopleRemoteMediator
import com.example.starwarsencyclopedia.data.PlanetRemoteMediator
import com.example.starwarsencyclopedia.data.SpeciesRemoteMediator
import com.example.starwarsencyclopedia.data.local.StarWarsDatabase
import com.example.starwarsencyclopedia.data.local.films.FilmEntity
import com.example.starwarsencyclopedia.data.local.people.PersonEntity
import com.example.starwarsencyclopedia.data.local.planets.PlanetEntity
import com.example.starwarsencyclopedia.data.local.species.SpeciesEntity
import com.example.starwarsencyclopedia.data.remote.StarWarsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

private const val SWAPI_BASE_URL = "https://swapi.py4e.com/api/"
private const val SWAPI_PAGE_SIZE = 10
private const val DB_NAME = "starwars.db"

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStarWarsApi(): StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(SWAPI_BASE_URL)
            .addConverterFactory(
                Json.asConverterFactory(
                    contentType = "application/json; charset=UTF8".toMediaType()
                )
            )
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStarWarsDb(@ApplicationContext context: Context): StarWarsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = StarWarsDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    @Provides
    fun providePeoplesPager(
        starWarsDb: StarWarsDatabase,
        starWarsApi: StarWarsApi
    ): Pager<Int, PersonEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = SWAPI_PAGE_SIZE
            ),
            remoteMediator = PeopleRemoteMediator(
                starWarsDb = starWarsDb,
                starWarsApi = starWarsApi
            ),
            pagingSourceFactory = {
                starWarsDb.personDao.pagingSource()
            }
        )
    }

    @Provides
    fun provideFilmsPager(
        starWarsDb: StarWarsDatabase,
        starWarsApi: StarWarsApi
    ): Pager<Int, FilmEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = SWAPI_PAGE_SIZE
            ),
            remoteMediator = FilmRemoteMediator(
                starWarsDb = starWarsDb,
                starWarsApi = starWarsApi
            ),
            pagingSourceFactory = {
                starWarsDb.filmDao.pagingSource()
            }
        )
    }

    @Provides
    fun providePlanetsPager(
        starWarsDb: StarWarsDatabase,
        starWarsApi: StarWarsApi
    ): Pager<Int, PlanetEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = SWAPI_PAGE_SIZE
            ),
            remoteMediator = PlanetRemoteMediator(
                starWarsDb = starWarsDb,
                starWarsApi = starWarsApi
            ),
            pagingSourceFactory = {
                starWarsDb.planetDao.pagingSource()
            }
        )
    }

    @Provides
    fun provideSpeciesPager(
        starWarsDb: StarWarsDatabase,
        starWarsApi: StarWarsApi
    ): Pager<Int, SpeciesEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = SWAPI_PAGE_SIZE
            ),
            remoteMediator = SpeciesRemoteMediator(
                starWarsDb = starWarsDb,
                starWarsApi = starWarsApi
            ),
            pagingSourceFactory = {
                starWarsDb.speciesDao.pagingSource()
            }
        )
    }
}