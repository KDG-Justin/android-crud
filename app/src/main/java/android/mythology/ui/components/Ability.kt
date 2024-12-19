package android.mythology.ui.components

import android.mythology.model.ability.Ability
import android.mythology.ui.AbilityState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Abilities(abilityState: AbilityState, characterId: Int?){

    when (abilityState) {
        is AbilityState.Loading -> Text(text = "Loading...")
        is AbilityState.Success -> AbilitiesResult(abilities = abilityState.abilities, characterId)
        is AbilityState.Error -> Text(text = "No abilities")
    }
}

@Composable
fun AbilitiesResult(abilities: List<Ability>, characterId: Int?){
    val filteredAbilities = abilities.filter { a -> a.mythicalCharacterId == characterId }
    LazyRow {
        items(filteredAbilities) {ability ->
            AbilityCard(
                ability = ability,
                modifier = Modifier.padding(6.dp)
            )
        }

    }
}

@Composable
fun AbilityCard(ability: Ability, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Text(
                text = ability.name,
                modifier = Modifier.padding(start = 6.dp,end = 6.dp, top = 2.dp, bottom = 2.dp),
            )
            Text(
                text = "Power: ${ability.power.toString()}",
                modifier = Modifier.padding(start = 6.dp,end = 6.dp, top = 2.dp, bottom = 2.dp),
            )
            Text(
                text = "Type: ${ability.type}",
                modifier = Modifier.padding(start = 6.dp,end = 6.dp, top = 2.dp, bottom = 2.dp),
            )
        }
    }
}