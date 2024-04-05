package com.example.starwarsencyclopedia.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people")
    suspend fun getPeople(
        @Query("page") page: Int
    ): PeopleResponse

}