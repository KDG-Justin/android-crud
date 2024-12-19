package android.mythology.model.ability

import kotlinx.serialization.Serializable

@Serializable
data class Ability (
    val id: Int,
    val mythicalCharacterId: Int,
    val name: String,
    val power: Int?,
    val type: AbilityType
)