package com.example.starwarsencyclopedia.data.remote

import com.example.starwarsencyclopedia.data.remote.films.FilmsResponse
import com.example.starwarsencyclopedia.data.remote.people.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people")
    suspend fun getPeople(
        @Query("page") page: Int
    ): PeopleResponse

    @GET("films")
    suspend fun getFilms(
        @Query("page") page: Int
    ): FilmsResponse
}