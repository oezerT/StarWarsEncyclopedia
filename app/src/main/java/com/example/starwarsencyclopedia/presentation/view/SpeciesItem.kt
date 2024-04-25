package com.example.starwarsencyclopedia.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwarsencyclopedia.domain.model.Species

@Composable
fun SpeciesItem(species: Species) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = species.name, style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            Text(text = "Average Height: ${species.averageHeight}")
            Text(text = "Average Lifespan: ${species.averageLifespan}")
            Text(text = "Classification: ${species.classification}")
            Text(text = "Designation: ${species.designation}")
            Text(text = "Eye Color: ${species.eyeColors}")
            Text(text = "Hair Color: ${species.hairColors}")
            Text(text = "Skin Color: ${species.skinColors}")
            Text(text = "Language: ${species.language}")
        }
    }
}

@Composable
@Preview
fun SpeciesItemPreview() {
    SpeciesItem(
        species = wookie
    )
}

val wookie = Species(
    name = "Wookie",
    classification = "mammal",
    designation = "sentient",
    averageHeight = "210",
    skinColors = "gray",
    hairColors = "black, brown",
    eyeColors = "blue, green, yellow, brown, golden, red",
    averageLifespan = "400",
    language = "Shyriiwook",
)

