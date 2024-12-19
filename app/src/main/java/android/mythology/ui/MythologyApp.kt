package android.mythology.ui

import android.annotation.SuppressLint
import android.mythology.ui.screens.Character
import android.mythology.ui.screens.Characters
import android.mythology.ui.components.TopBarMythology
import android.mythology.ui.screens.AddCharacter
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun MythologyApp(mythologyViewModel: MythologyViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenTitle = when (backStackEntry?.destination?.route) {
        "characters" -> "Characters"
        "character/{id}" -> "Detail"
        else -> "Mythology" // won't be used probably
    }
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(topBar = {
        TopBarMythology(
            currentScreen = currentScreenTitle,
            canNavigateBack = canNavigateBack,
            navigateUp = { navController.navigateUp() }
        )
    }) {
        NavHost(navController = navController, startDestination = "characters") {
            composable("characters") { Characters(navController = navController, mythologyState = mythologyViewModel.mythologyState, mythologyViewModel = mythologyViewModel) }
            composable("character/{id}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("id")
                characterId?.let { Character(id = characterId.toInt(), mythologyViewModel = mythologyViewModel) }
            }
            composable("characters/add") { AddCharacter(mythologyViewModel = mythologyViewModel, navController = navController)}
        }
    }
}