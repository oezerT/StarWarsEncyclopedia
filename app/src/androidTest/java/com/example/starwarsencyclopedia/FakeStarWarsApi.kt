package com.example.starwarsencyclopedia

import com.example.starwarsencyclopedia.data.remote.StarWarsApi
import com.example.starwarsencyclopedia.data.remote.model.CategoryResponse
import com.example.starwarsencyclopedia.data.remote.model.FilmDto
import com.example.starwarsencyclopedia.data.remote.model.PersonDto
import com.example.starwarsencyclopedia.data.remote.model.PlanetDto
import com.example.starwarsencyclopedia.data.remote.model.SpeciesDto
import java.io.IOException

private const val SWAPI_PAGE_SIZE = 10

class FakeStarWarsApi : StarWarsApi {

    var films = emptyList<FilmDto>()
    var people = emptyList<PersonDto>()
    var planets = emptyList<PlanetDto>()
    var species = emptyList<SpeciesDto>()

    var failureMsg: String? = null

    fun clearCategories() {
        films = emptyList()
        people = emptyList()
        planets = emptyList()
        species = emptyList()
    }

    override suspend fun getPeople(page: Int): CategoryResponse<PersonDto> {
        failureMsg?.let {
            throw IOException(it)
        }
        return CategoryResponse(
            count = people.size,
            next = "".takeIf { people.size > SWAPI_PAGE_SIZE },
            previous = null,
            results = people
        )
    }

    override suspend fun getFilms(page: Int): CategoryResponse<FilmDto> {
        failureMsg?.let {
            throw IOException(it)
        }
        return CategoryResponse(
            count = films.size,
            next = "".takeIf { films.size > SWAPI_PAGE_SIZE },
            previous = null,
            results = films
        )
    }

    override suspend fun getPlanets(page: Int): CategoryResponse<PlanetDto> {
        failureMsg?.let {
            throw IOException(it)
        }
        return CategoryResponse(
            count = planets.size,
            next = "".takeIf { planets.size > SWAPI_PAGE_SIZE },
            previous = null,
            results = planets
        )
    }

    override suspend fun getSpecies(page: Int): CategoryResponse<SpeciesDto> {
        failureMsg?.let {
            throw IOException(it)
        }
        return CategoryResponse(
            count = species.size,
            next = "".takeIf { species.size > SWAPI_PAGE_SIZE },
            previous = null,
            results = species
        )
    }
}

fun mockFilms() = mutableListOf<FilmDto>().apply {
    repeat(11) {
        this.add(
            FilmDto(
                title = "",
                episodeId = 1,
                openingCrawl = "",
                director = "",
                producer = "",
                releaseDate = "",
                characters = emptyList(),
                planets = emptyList(),
                starships = emptyList(),
                vehicles = emptyList(),
                species = emptyList(),
                created = "",
                edited = "",
                url = ""
            )
        )
    }
}

fun mockPeople() = mutableListOf<PersonDto>().apply {
    repeat(11) {
        this.add(
            PersonDto(
                created = "",
                edited = "",
                url = "",
                name = "",
                height = "",
                mass = "",
                hairColor = "",
                skinColor = "",
                eyeColor = "",
                birthYear = "",
                gender = "",
                homeworld = "",
                films = emptyList(),
                starships = emptyList(),
                vehicles = emptyList(),
                species = emptyList(),
            )
        )
    }
}

fun mockPlanets() = mutableListOf<PlanetDto>().apply {
    repeat(11) {
        this.add(
            PlanetDto(
                created = "",
                edited = "",
                url = "",
                name = "",
                rotationPeriod = "",
                orbitalPeriod = "",
                diameter = "",
                climate = "",
                gravity = "",
                terrain = "",
                surfaceWater = "",
                population = "",
            )
        )
    }
}

fun mockSpecies() = mutableListOf<SpeciesDto>().apply {
    repeat(11) {
        this.add(
            SpeciesDto(
                created = "",
                edited = "",
                url = "",
                name = "",
                classification = "",
                designation = "",
                averageHeight = "",
                skinColors = "",
                hairColors = "",
                eyeColors = "",
                averageLifespan = "",
                homeworld = "",
                language = ""
            )
        )
    }
}
