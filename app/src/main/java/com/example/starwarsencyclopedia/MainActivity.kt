package com.example.starwarsencyclopedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwarsencyclopedia.presentation.StarWarsEncyclopediaViewModel
import com.example.starwarsencyclopedia.presentation.view.PeopleList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: StarWarsEncyclopediaViewModel = viewModel()
            val peopleData = viewModel.peoplePagingFlow.collectAsLazyPagingItems()
            MaterialTheme {
                PeopleList(peopleData)
            }
        }
    }
}