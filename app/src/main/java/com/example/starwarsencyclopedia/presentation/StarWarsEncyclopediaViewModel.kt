package com.example.starwarsencyclopedia.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.starwarsencyclopedia.data.local.films.FilmEntity
import com.example.starwarsencyclopedia.data.local.people.PersonEntity
import com.example.starwarsencyclopedia.data.local.planets.PlanetEntity
import com.example.starwarsencyclopedia.data.local.species.SpeciesEntity
import com.example.starwarsencyclopedia.data.util.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class StarWarsEncyclopediaViewModel @Inject constructor(
    peoplePager: Pager<Int, PersonEntity>,
    filmsPager: Pager<Int, FilmEntity>,
    planetsPager: Pager<Int, PlanetEntity>,
    speciesPager: Pager<Int, SpeciesEntity>,
) : ViewModel() {
    val peoplePagingFlow = peoplePager.flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }.cachedIn(viewModelScope)

    val filmsPagingFlow = filmsPager.flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }.cachedIn(viewModelScope)

    val planetsPagingFlow = planetsPager.flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }.cachedIn(viewModelScope)

    val speciesPagingFlow = speciesPager.flow.map { pagingData ->
        pagingData.map {
            it.toDomain()
        }
    }.cachedIn(viewModelScope)
}