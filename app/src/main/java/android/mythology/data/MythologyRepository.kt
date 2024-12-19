package android.mythology.data

import android.mythology.model.ability.Ability
import android.mythology.model.mythicalcharacter.MythicalCharacter
import android.mythology.network.MythologyApiService


interface MythologyRepository {
    suspend fun getCharacters() : List<MythicalCharacter>

    suspend fun getAbilities() : List<Ability>

    suspend fun addCharacter(character: MythicalCharacter)

    suspend fun updateCharacter(id: Int, character: MythicalCharacter)

    suspend fun deleteCharacter(id: Int)
}

class NetworkMythologyRepository(
    private val mythologyApiService: MythologyApiService
) : MythologyRepository {
    override suspend fun addCharacter(character: MythicalCharacter) {
        mythologyApiService.addCharacter(character)
    }

    override suspend fun deleteCharacter(id: Int) {
        mythologyApiService.deleteCharacter(id)
    }

    override suspend fun getCharacters(): List<MythicalCharacter> {
        return mythologyApiService.getCharacters()
    }

    override suspend fun getAbilities(): List<Ability> {
        return mythologyApiService.getAbilities()
    }

    override suspend fun updateCharacter(id: Int, character: MythicalCharacter) {
       mythologyApiService.updateCharacter(id, character)
    }
}