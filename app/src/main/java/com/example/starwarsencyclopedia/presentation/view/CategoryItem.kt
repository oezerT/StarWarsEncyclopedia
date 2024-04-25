package com.example.starwarsencyclopedia.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwarsencyclopedia.domain.model.Film
import com.example.starwarsencyclopedia.domain.model.Person
import com.example.starwarsencyclopedia.domain.model.Planet
import com.example.starwarsencyclopedia.domain.model.Species

@Composable
fun CategoryItem(
    title: String,
    content: @Composable () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            content()
        }
    }
}

@Composable
fun FilmItem(film: Film) {
    CategoryItem(title = film.title) {
        Text(text = "Episode: ${film.episodeId}")
        Text(text = "Release Date: ${film.releaseDate}")
        Text(text = "Director: ${film.director}")
        Text(text = "Producer: ${film.producer}")
        Text(text = "Opening Crawl: \n${film.openingCrawl}")
    }
}

@Composable
fun PersonItem(person: Person) {
    CategoryItem(title = person.name) {
        Text(text = "Gender: ${person.gender}")
        Text(text = "Birth Year: ${person.birthYear}")
        Text(text = "Height: ${person.height}")
        Text(text = "Mass: ${person.mass}")
        Text(text = "Eye Color: ${person.eyeColor}")
        Text(text = "Skin Color: ${person.skinColor}")
    }
}

@Composable
fun PlanetItem(planet: Planet) {
    CategoryItem(title = planet.name) {
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

@Composable
fun SpeciesItem(species: Species) {
    CategoryItem(title = species.name) {
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

@Composable
@Preview
fun PersonItemPreview() {
    PersonItem(
        person = Person(
            name = "Luke Skywalker",
            height = "172",
            mass = "77",
            hairColor = "blond",
            skinColor = "fair",
            eyeColor = "blue",
            birthYear = "19BBY",
            gender = "male",
        )
    )
}

@Composable
@Preview
fun FilmItemPreview() {
    FilmItem(film = aNewHope)
}


val aNewHope = Film(
    title = "A New Hope",
    episodeId = 4,
    openingCrawl = "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
    director = "George Lucas",
    producer = "Gary Kurtz, Rick McCallum",
    releaseDate = "1977-05-25",
)

@Composable
@Preview
fun PlanetItemPreview() {
    PlanetItem(
        planet = Planet(
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
    )
}

@Composable
@Preview
fun SpeciesItemPreview() {
    SpeciesItem(
        species = Species(
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
    )
}
