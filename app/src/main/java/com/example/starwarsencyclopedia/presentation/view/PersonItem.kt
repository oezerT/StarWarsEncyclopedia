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
import com.example.starwarsencyclopedia.domain.model.Person

@Composable
fun PersonItem(person: Person) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(text = person.name, style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            Text(text = "Gender: ${person.gender}")
            Text(text = "Birth Year: ${person.birthYear}")
            Text(text = "Height: ${person.height}")
            Text(text = "Mass: ${person.mass}")
            Text(text = "Eye Color: ${person.eyeColor}")
            Text(text = "Skin Color: ${person.skinColor}")
        }
    }
}

@Composable
@Preview
fun PersonItemPreview() {
    PersonItem(person = lukeSkywalker)
}

val lukeSkywalker = Person(
    name = "Luke Skywalker",
    height = "172",
    mass = "77",
    hairColor = "blond",
    skinColor = "fair",
    eyeColor = "blue",
    birthYear = "19BBY",
    gender = "male",
)