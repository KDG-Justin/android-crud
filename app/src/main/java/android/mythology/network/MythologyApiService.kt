package android.mythology.network

import android.mythology.model.ability.Ability
import android.mythology.model.mythicalcharacter.MythicalCharacter
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface MythologyApiService {
    @GET("mythicalCharacters")
    suspend fun getCharacters(): List<MythicalCharacter>

    @GET("abilities")
    suspend fun getAbilities(): List<Ability>

    @POST("mythicalCharacters")
    suspend fun addCharacter(
        @Body character: MythicalCharacter
    ): Response<MythicalCharacter>

    @PUT("mythicalCharacters/{id}")
    suspend fun updateCharacter(
        @Path("id") id: Int,
        @Body character: MythicalCharacter
    ): Response<MythicalCharacter>

    @DELETE("mythicalCharacters/{id}")
    suspend fun deleteCharacter(
        @Path("id") id: Int
    ): Response<Unit>
}