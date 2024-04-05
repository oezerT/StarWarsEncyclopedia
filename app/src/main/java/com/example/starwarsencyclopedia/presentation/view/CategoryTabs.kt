package com.example.starwarsencyclopedia.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwarsencyclopedia.presentation.StarWarsEncyclopediaViewModel
import com.example.starwarsencyclopedia.presentation.view.films.FilmsList
import com.example.starwarsencyclopedia.presentation.view.people.PeopleList


@Composable
fun CategoryTabs() {
    val viewModel: StarWarsEncyclopediaViewModel = viewModel()
    val peopleData = viewModel.peoplePagingFlow.collectAsLazyPagingItems()
    val filmsData = viewModel.filmsPagingFlow.collectAsLazyPagingItems()

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Films", "People")
    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title) }
                )
            }
        }
        when (state) {
            0 -> FilmsList(films = filmsData)
            1 -> PeopleList(people = peopleData)
        }
    }
}