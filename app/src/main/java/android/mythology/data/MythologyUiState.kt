package android.mythology.data

import android.mythology.model.mythicalcharacter.MythicalCharacter

data class MythologyUiState(
    val characterList : List<MythicalCharacter> = emptyList(),
    val currentId : Int = 1,
    var selectedCharacter: MythicalCharacter ?= null,
    val hpInput : String = "",
    val isLoading : Boolean = false,
    val isError : Boolean = false
)
