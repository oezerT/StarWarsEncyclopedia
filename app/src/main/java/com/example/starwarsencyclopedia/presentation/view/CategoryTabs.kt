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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwarsencyclopedia.presentation.StarWarsEncyclopediaViewModel


@Composable
fun CategoryTabs() {
    val viewModel: StarWarsEncyclopediaViewModel = viewModel()
    val peopleData = viewModel.peoplePagingFlow.collectAsLazyPagingItems()
    val filmsData = viewModel.filmsPagingFlow.collectAsLazyPagingItems()
    val planetsData = viewModel.planetsPagingFlow.collectAsLazyPagingItems()

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Films", "People", "Planets")
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
            0 -> CategoryList(category = filmsData as LazyPagingItems<Any>)
            1 -> CategoryList(category = peopleData as LazyPagingItems<Any>)
            2 -> CategoryList(category = planetsData as LazyPagingItems<Any>)
        }
    }
}