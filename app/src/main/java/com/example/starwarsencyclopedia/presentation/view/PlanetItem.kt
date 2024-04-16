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
import com.example.starwarsencyclopedia.domain.model.Planet

@Composable
fun PlanetItem(planet: Planet) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = planet.name, style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            Text(text = "Climate: ${planet.climate}")
            Text(text = "Diameter: ${planet.diameter}")
            Text(text = "Gravity: ${planet.gravity}")
            Text(text = "Orbital Period: ${planet.orbitalPeriod}")
            Text(text = "Population: ${planet.population}")
            Text(text = "Rotation Period: ${planet.rotationPeriod}")
            Text(text = "Surface Water: ${planet.surfaceWater}")
            Text(text = "Terrain: ${planet.terrain}")
        }
    }
}

@Composable
@Preview
fun PlanetItemPreview() {
    PlanetItem(planet = tatooine)
}

val tatooine = Planet(
    name = "Tatooine",
    rotationPeriod = "23",
    orbitalPeriod = "304",
    diameter = "10465",
    climate = "arid",
    gravity = "1 standard",
    terrain = "desert",
    surfaceWater = "1",
    population = "200000",
)