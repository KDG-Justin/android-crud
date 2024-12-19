package android.mythology.ui

import android.icu.lang.UCharacter.toLowerCase
import android.mythology.data.MythologyRepository
import android.mythology.data.MythologyUiState
import android.mythology.model.ability.Ability
import android.mythology.model.mythicalcharacter.MythicalCharacter
import android.mythology.model.mythicalcharacter.Mythology
import retrofit2.HttpException
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDate
import java.io.IOException
import javax.inject.Inject

sealed interface MythologyState{
    data class Success(
        val characters: List<MythicalCharacter>
    ) : MythologyState
    object Error : MythologyState
    object Loading : MythologyState
}

sealed interface AbilityState{
    data class Success(
        val abilities: List<Ability>
    ) : AbilityState
    object Error : AbilityState
    object Loading : AbilityState
}


@HiltViewModel
class MythologyViewModel @Inject constructor(private val mythologyRepository: MythologyRepository) : ViewModel(){
    private val characters  = mutableStateListOf<MythicalCharacter>()
    private val abilities  = mutableStateListOf<Ability>()
    private val _uiState = MutableStateFlow(MythologyUiState())
    val uiState: StateFlow<MythologyUiState> = _uiState.asStateFlow()


    var mythologyState: MythologyState by mutableStateOf(MythologyState.Loading)
        private set
    var abilityState: AbilityState by mutableStateOf(AbilityState.Loading)
        private set

    var currentId by mutableIntStateOf(1)
    var hpInput by mutableStateOf("")
        private set

    init {
        getCharacters()
        getAbilities()
    }

    fun updateUiState() {
        _uiState.value = MythologyUiState(
            characterList = characters,
            currentId = currentId,
            hpInput = hpInput
        )
    }


    fun getCharacters(){
        viewModelScope.launch {
            mythologyState = MythologyState.Loading
            mythologyState = try {
                val list = mythologyRepository.getCharacters()
                list.forEach { c -> characters.add(c) }
                MythologyState.Success(list)
            } catch (e: IOException) {
                MythologyState.Error
            } catch (e: HttpException) {
                MythologyState.Error
            }
        }
    }

    fun getAbilities(){
        viewModelScope.launch {
            abilityState = AbilityState.Loading
            abilityState = try {
                val list = mythologyRepository.getAbilities()
                list.forEach { c -> abilities.add(c) }
                AbilityState.Success(list)
            } catch (e: IOException) {
                AbilityState.Error
            } catch (e: HttpException) {
                AbilityState.Error
            }
        }
    }

    fun getCharacter(id: Int) : MythicalCharacter?{

        Log.i("Get Character", "Looking for character with id [${id}] ...")
        return characters.find { c -> c.id == id }
    }

    fun addCharacter(name: String, release : String, playable: Boolean, mythology: String, hitPoints: Int){
        viewModelScope.launch {
            val id = characters.last().id + 1
            val rating = 0.0
            val mythologyEnum = Mythology.entries.firstOrNull { m -> toLowerCase(m.name) == toLowerCase(mythology)} ?: Mythology.UNKNOWN

            val createdCharacter = MythicalCharacter(
                id = id, image = "", name = name, release = release.toLocalDate(),
                playable = playable, mythology = mythologyEnum, rating = rating, hitPoints = hitPoints)

            mythologyRepository.addCharacter(createdCharacter) // intern veranderen

            characters.add(createdCharacter)
            mythologyState = MythologyState.Success(characters.toList()) // extern veranderen
            updateUiState()
            Log.i("Add Character", "Character: [${createdCharacter.name}] Has been added!")
        }
    }

    fun deleteCharacter(id: Int) {
        viewModelScope.launch {
            try {
                mythologyRepository.deleteCharacter(id) // intern veranderen
                characters.removeAll { c -> c.id == id }
                mythologyState = MythologyState.Success(characters.toList()) // extern veranderen
                updateUiState()
                Log.i("Delete Character", "Character: [${id}] Has been removed!")
            }catch(e : NullPointerException){
                Log.i("Delete Character", "This character does not exist anymore!")
            }
        }
    }

    fun updateCharacterHp(updatedCharacter: MythicalCharacter){
        viewModelScope.launch {
            val index = characters.indexOfFirst { c -> c.id == updatedCharacter.id }
            if (index != -1) {
                mythologyRepository.updateCharacter(updatedCharacter.id, updatedCharacter) // intern veranderen
                characters[index] = updatedCharacter
                mythologyState = MythologyState.Success(characters.toList()) // extern veranderen
                updateUiState()
                Log.i("Update Character", "Character: [${updatedCharacter.name}] Has been updated!")
                Log.i("Update Character", "UPDATE: [${updatedCharacter.hitPoints}] HP")
            } else {
                Log.i("Update Character", "ERROR: Could not update your character!")
            }
        }
    }

    fun updateHpInput(newHpInput: String) {
        hpInput = newHpInput
        updateUiState()
    }
}
