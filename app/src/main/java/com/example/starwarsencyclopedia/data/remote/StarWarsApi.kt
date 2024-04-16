package com.example.starwarsencyclopedia.data.remote

import com.example.starwarsencyclopedia.data.remote.model.CategoryResponse
import com.example.starwarsencyclopedia.data.remote.model.FilmDto
import com.example.starwarsencyclopedia.data.remote.model.PersonDto
import com.example.starwarsencyclopedia.data.remote.model.PlanetDto
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people")
    suspend fun getPeople(
        @Query("page") page: Int
    ): CategoryResponse<PersonDto>

    @GET("films")
    suspend fun getFilms(
        @Query("page") page: Int
    ): CategoryResponse<FilmDto>

    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int
    ): CategoryResponse<PlanetDto>

}