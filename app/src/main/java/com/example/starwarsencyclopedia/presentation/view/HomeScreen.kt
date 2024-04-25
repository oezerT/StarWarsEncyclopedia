package com.example.starwarsencyclopedia.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwarsencyclopedia.domain.model.Film
import com.example.starwarsencyclopedia.domain.model.Person
import com.example.starwarsencyclopedia.domain.model.Planet
import com.example.starwarsencyclopedia.domain.model.Species
import com.example.starwarsencyclopedia.presentation.StarWarsEncyclopediaViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen() {
    val viewModel: StarWarsEncyclopediaViewModel = viewModel()

    HomeScreen(
        peopleData = viewModel.peoplePagingFlow.collectAsLazyPagingItems(),
        filmsData = viewModel.filmsPagingFlow.collectAsLazyPagingItems(),
        planetsData = viewModel.planetsPagingFlow.collectAsLazyPagingItems(),
        speciesData = viewModel.speciesPagingFlow.collectAsLazyPagingItems(),
    )
}

@Composable
fun HomeScreen(
    peopleData: LazyPagingItems<Person>,
    filmsData: LazyPagingItems<Film>,
    planetsData: LazyPagingItems<Planet>,
    speciesData: LazyPagingItems<Species>
) {

    val navController = rememberNavController()
    var selectedItem by remember { mutableIntStateOf(0) }

    val filmsListState = rememberLazyListState()
    val peopleListState = rememberLazyListState()
    val planetsListState = rememberLazyListState()
    val speciesListState = rememberLazyListState()

    Scaffold(
        content = { paddingValues ->
            NavHost(navController, startDestination = Screens.FILMS.name) {
                Screens.entries.forEach { screen ->
                    composable(screen.name) {
                        when (screen) {
                            Screens.FILMS -> CategoryList(
                                modifier = Modifier.padding(paddingValues),
                                category = filmsData,
                                listState = filmsListState
                            )

                            Screens.PEOPLE -> CategoryList(
                                modifier = Modifier.padding(paddingValues),
                                category = peopleData,
                                listState = peopleListState
                            )

                            Screens.PLANETS -> CategoryList(
                                modifier = Modifier.padding(paddingValues),
                                category = planetsData,
                                listState = planetsListState
                            )

                            Screens.SPECIES -> CategoryList(
                                modifier = Modifier.padding(paddingValues),
                                category = speciesData,
                                listState = speciesListState
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomBar(
                selectedItem = selectedItem,
                onSelectItem = {
                    selectedItem = it.ordinal
                    navController.navigate(it.name)
                }
            )
        }
    )
}


@Composable
private fun BottomBar(
    onSelectItem: (Screens) -> Unit,
    selectedItem: Int,
) {
    NavigationBar {
        Screens.entries.forEach {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = when (it) {
                            Screens.FILMS -> Icons.Filled.Star
                            Screens.PEOPLE -> Icons.Default.Person
                            Screens.PLANETS -> Icons.Default.LocationOn
                            Screens.SPECIES -> Icons.Default.Face
                        },
                        contentDescription = it.name
                    )

                },
                label = { Text(it.name) },
                selected = selectedItem == it.ordinal,
                onClick = { onSelectItem(it) }
            )
        }
    }
}

@Suppress("UNCHECKED_CAST")
@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(
        filmsData = flowOf(
            PagingData.from(
                data = listOf(
                    aNewHope,
                    aNewHope,
                    aNewHope
                ),
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(true),
                    append = LoadState.NotLoading(true),
                    prepend = LoadState.NotLoading(true),
                ),
            )
        ).collectAsLazyPagingItems(),
        peopleData = flowOf(
            PagingData.from(data = emptyList())
        ).collectAsLazyPagingItems() as LazyPagingItems<Person>,
        planetsData = flowOf(
            PagingData.from(data = emptyList())
        ).collectAsLazyPagingItems() as LazyPagingItems<Planet>,
        speciesData = flowOf(
            PagingData.from(data = emptyList())
        ).collectAsLazyPagingItems() as LazyPagingItems<Species>,
    )
}

enum class Screens {
    FILMS,
    PEOPLE,
    PLANETS,
    SPECIES
}