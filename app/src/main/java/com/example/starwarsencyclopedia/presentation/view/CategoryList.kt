package com.example.starwarsencyclopedia.presentation.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwarsencyclopedia.domain.model.Film
import com.example.starwarsencyclopedia.domain.model.Person
import com.example.starwarsencyclopedia.domain.model.Planet
import com.example.starwarsencyclopedia.domain.model.Species
import kotlinx.coroutines.flow.flowOf

@Composable
fun CategoryList(
    modifier: Modifier,
    category: LazyPagingItems<*>,
    listState: LazyListState,
) {
    (category.loadState.refresh as? LoadState.Error)?.error?.message?.let {
        ErrorToast(it)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        if (category.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(15.dp),
                state = listState,
            ) {
                items(
                    count = category.itemCount,
                ) { index ->
                    val item = category[index]
                    item?.let {
                        when (it) {
                            is Film -> FilmItem(film = it)
                            is Person -> PersonItem(person = it)
                            is Planet -> PlanetItem(planet = it)
                            is Species -> SpeciesItem(species = it)
                        }
                    }
                }

                item {
                    if (category.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorToast(errorMessage: String) {
    val context = LocalContext.current
    Toast.makeText(
        context,
        "Error: $errorMessage",
        Toast.LENGTH_LONG
    ).show()
}

@Composable
@Preview
fun CategoryListPreview() {
    CategoryList(
        Modifier,
        flowOf(
            PagingData.from(
                data = listOf(
                    lukeSkywalker,
                    lukeSkywalker,
                    lukeSkywalker
                ),
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(true),
                    append = LoadState.NotLoading(true),
                    prepend = LoadState.NotLoading(true),
                ),
            )
        ).collectAsLazyPagingItems() as LazyPagingItems<*>,
        LazyListState()
    )
}