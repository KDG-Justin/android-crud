package android.mythology.ui.screens

import android.mythology.ui.MythologyViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun AddCharacter(mythologyViewModel: MythologyViewModel, navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var release by remember { mutableStateOf("") }
    var playable by remember { mutableStateOf(false) }
    var mythology by remember { mutableStateOf("") }
    var hitPoints by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(top = 70.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Create your Character", style = TextStyle(fontSize = 32.sp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = release,
            onValueChange = { release = it },
            label = { Text("Release") },
            placeholder = { Text("YYYY-MM-DD") }, // Placeholder for example
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = mythology,
            onValueChange = { mythology = it },
            label = { Text("Mythology") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = hitPoints.toString(),
            onValueChange = { hitPoints = it.toIntOrNull() ?: 0 },
            label = { Text("Hit Points") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
        ) {
            Text("Playable")
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = playable,
                onCheckedChange = { playable = it }
            )
        }
        Button(onClick = {
            mythologyViewModel.addCharacter(name, release, playable, mythology, hitPoints)
            navController.popBackStack()
        }) {
            Text("Create")
        }
    }
}