package android.mythology.ui.screens

import android.annotation.SuppressLint
import android.mythology.R
import android.mythology.ui.MythologyViewModel
import android.mythology.ui.components.Abilities
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Character(
    id: Int,
    mythologyViewModel: MythologyViewModel,
) {
    val uiState by mythologyViewModel.uiState.collectAsState()
    val character = mythologyViewModel.getCharacter(id)

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            if (character?.image != null){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_connection_error),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = "image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(width = 250.dp, height = 333.dp)
                )
            }else{
                Image( painter = painterResource(R.drawable.smite), contentDescription = character?.name, contentScale = ContentScale.FillBounds, modifier = Modifier.size(width = 250.dp, height = 333.dp))
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 12.dp)
                .background(Color.LightGray)
        ) {
            Column(
                modifier = Modifier
                    .width(250.dp)
                    .padding(start = 8.dp)
            ) {
                Text(text = "Name: ${character?.name}", style = TextStyle(fontSize = 18.sp))
                Text(text = "HP: ${character?.hitPoints}", style = TextStyle(fontSize = 18.sp))
                Text(text = "Rating: ${character?.rating}", style = TextStyle(fontSize = 18.sp))
                Text(text = "Abilities:", style = TextStyle(fontSize = 18.sp))
                Abilities(abilityState = mythologyViewModel.abilityState, character?.id)
            }
        }

        Box(
            modifier = Modifier
                .width(250.dp)
                .padding(top = 16.dp, bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), // pakt alle breedte van de box
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EditHP(
                    value = uiState.hpInput,
                    onValueChanged = { mythologyViewModel.updateHpInput(it) },
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = {
                        if (uiState.hpInput.isNotBlank()) {
                            val currentCharacter =
                                uiState.characterList.find { c -> c.id == uiState.currentId }
                            currentCharacter?.let {
                                it.hitPoints = uiState.hpInput.toInt()

                                mythologyViewModel.updateCharacterHp(it)
                            }

                            mythologyViewModel.updateHpInput("")
                        }
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "Modify")
                }
            }
        }
    }


}

@Composable
@Preview
fun TestCharacter() {
    //Character(id = 1)
}


@Composable
fun EditHP(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text("Hit Points") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
