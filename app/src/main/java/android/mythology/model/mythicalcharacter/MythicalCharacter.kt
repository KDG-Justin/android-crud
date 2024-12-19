package android.mythology.model.mythicalcharacter

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class MythicalCharacter (
    val id: Int,
    val image: String?,
    val name: String,
    val release: LocalDate,
    val playable: Boolean,
    val mythology: Mythology,
    var hitPoints: Int,
    @SerialName(value = "ratings") val rating: Double
)