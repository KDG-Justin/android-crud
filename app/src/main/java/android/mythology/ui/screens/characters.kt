package android.mythology.ui.screens

import android.annotation.SuppressLint
import android.mythology.R
import android.mythology.model.mythicalcharacter.MythicalCharacter
import android.mythology.ui.MythologyState
import android.mythology.ui.MythologyViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Characters(
    navController: NavHostController,
    mythologyState: MythologyState,
    mythologyViewModel: MythologyViewModel
) {
    when (mythologyState) {
        is MythologyState.Loading -> LoadingScreen()
        is MythologyState.Success -> ResultScreen(
            navController = navController,
            characters = mythologyState.characters,
            mythologyViewModel = mythologyViewModel
        )

        is MythologyState.Error -> ErrorScreen()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResultScreen(
    navController: NavHostController,
    characters: List<MythicalCharacter>,
    mythologyViewModel: MythologyViewModel
) {
    val scrollState = rememberScrollState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("characters/add") },
                modifier = Modifier.padding(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(top = 70.dp, start = 75.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 70.dp)
                ) {
                    characters.forEach { c ->
                        CharacterCard(
                            character = c,
                            navController = navController,
                            mythologyViewModel = mythologyViewModel
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(
    character: MythicalCharacter,
    navController: NavHostController,
    mythologyViewModel: MythologyViewModel
) {
    Card(onClick = { navController.navigate("character/${character.id}") }) {
        Column(horizontalAlignment = Alignment.Start) {
            Box {
                if (character.image != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(character.image)
                            .crossfade(true)
                            .build(),
                        error = painterResource(R.drawable.smite),
                        placeholder = painterResource(R.drawable.loading_img),
                        contentDescription = "image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(width = 250.dp, height = 333.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.ic_connection_error),
                        contentDescription = character.name,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(width = 250.dp, height = 333.dp)
                    )
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
                    Text(text = "Name: ${character.name}", style = TextStyle(fontSize = 18.sp))
                    Text(text = "HP: ${character.hitPoints}", style = TextStyle(fontSize = 18.sp))
                    Text(text = "Rating: ${character.rating}", style = TextStyle(fontSize = 18.sp))
                    Box {
                        Button(
                            onClick = {
                                mythologyViewModel.deleteCharacter(character.id)
                            },
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 82.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Remove")
                        }

                    }
                }
            }
        }
    }
}