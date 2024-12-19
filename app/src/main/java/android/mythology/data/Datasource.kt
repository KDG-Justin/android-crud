package android.mythology.data

import android.mythology.model.ability.Ability
import android.mythology.model.ability.AbilityType
import android.mythology.model.mythicalcharacter.MythicalCharacter
import android.mythology.model.mythicalcharacter.Mythology
import androidx.compose.runtime.mutableStateListOf
import java.time.LocalDate

class Datasource(){
    /*
    fun loadCharacters() : List<MythicalCharacter> {
        return mutableStateListOf<MythicalCharacter>(
            MythicalCharacter(
                id = 1,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/d/d7/SkinArt_Athena_Default.jpg/revision/latest/scale-to-width-down/250?cb=20210518193804",
                name = "Athena",
                release = LocalDate.parse("2021-04-12"),
                playable = true,
                mythology = Mythology.GREEK,
                hitPoints = 3500,
                rating = 9.4
            ),
            MythicalCharacter(
                id = 2,
                image = "https://static.wikia.nocookie.net/smite/images/1/16/Loki.jpg/revision/latest?cb=20160813174334",
                name = "Loki",
                release = LocalDate.parse("2021-05-22"),
                playable = true,
                mythology = Mythology.NORSE,
                hitPoints = 3000,
                rating = 9.1
            ),
            MythicalCharacter(
                id = 3,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/d/da/SkinArt_Hercules_Default.jpg/revision/latest/scale-to-width-down/250?cb=20160505052422",
                name = "Hercules",
                release = LocalDate.parse("2021-06-30"),
                playable = false,
                mythology = Mythology.GREEK,
                hitPoints = 4500,
                rating = 8.5
            ),
            MythicalCharacter(
                id = 4,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/5/59/SkinArt_Anubis_Default.jpg/revision/latest/scale-to-width-down/250?cb=20160505052018",
                name = "Anubis",
                release = LocalDate.parse("2021-07-15"),
                playable = true,
                mythology = Mythology.EGYPTIAN,
                hitPoints = 3200,
                rating = 7.8
            ),
            MythicalCharacter(
                id = 5,
                image = "https://static.wikia.nocookie.net/smite/images/2/23/Thor.jpg/revision/latest?cb=20140312065559",
                name = "Thor",
                release = LocalDate.parse("2021-08-20"),
                playable = true,
                mythology = Mythology.NORSE,
                hitPoints = 4000,
                rating = 9.9
            ),
            MythicalCharacter(
                id = 6,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/8/85/SkinArt_IxChel_Default.jpg/revision/latest?cb=20230418222055",
                name = "Ix Chel",
                release = LocalDate.parse("2021-09-10"),
                playable = true,
                mythology = Mythology.MAYAN,
                hitPoints = 2800,
                rating = 8.0
            ),
            MythicalCharacter(
                id = 7,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/5/5d/T_Awilix_Default_Card.png/revision/latest?cb=20141213034955",
                name = "Awilix",
                release = LocalDate.parse("2021-10-05"),
                playable = false,
                mythology = Mythology.MAYAN,
                hitPoints = 3200,
                rating = 7.5
            ),
            MythicalCharacter(
                id = 8,
                image = "https://static.wikia.nocookie.net/smite/images/1/15/CuChulainn.jpg/revision/latest?cb=20170614211851",
                name = "Cuchulainn",
                release = LocalDate.parse("2021-11-17"),
                playable = true,
                mythology = Mythology.CELTIC,
                hitPoints = 3900,
                rating = 8.7
            ),
            MythicalCharacter(
                id = 9,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/4/4c/SkinArt_Artio_Default.jpg/revision/latest/scale-to-width-down/250?cb=20170728125143",
                name = "Artio",
                release = LocalDate.parse("2021-12-03"),
                playable = true,
                mythology = Mythology.CELTIC,
                hitPoints = 3100,
                rating = 6.5
            ),
            MythicalCharacter(
                id = 10,
                image = "https://static.wikia.nocookie.net/smite/images/2/26/Ra.jpg/revision/latest?cb=20150114194336",
                name = "Ra",
                release = LocalDate.parse("2022-01-21"),
                playable = false,
                mythology = Mythology.EGYPTIAN,
                hitPoints = 3400,
                rating = 7.3
            ),
            MythicalCharacter(
                id = 11,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/4/49/T_Artemis_Default_Card.png/revision/latest?cb=20230516193125",
                name = "Artemis",
                release = LocalDate.parse("2022-02-14"),
                playable = true,
                mythology = Mythology.GREEK,
                hitPoints = 3800,
                rating = 8.9
            ),
            MythicalCharacter(
                id = 12,
                image = "https://static.wikia.nocookie.net/smiteesports_gamepedia_en/images/e/e9/The_MorriganProfile.png/revision/latest/scale-to-width-down/220?cb=20170726231816",
                name = "The Morrigan",
                release = LocalDate.parse("2022-03-19"),
                playable = true,
                mythology = Mythology.CELTIC,
                hitPoints = 3600,
                rating = 8.2
            ),
            MythicalCharacter(
                id = 13,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/f/f0/SkinArt_Cernunnos_Default.jpg/revision/latest/scale-to-width-down/250?cb=20170308234455",
                name = "Cernnunos",
                release = LocalDate.parse("2022-04-22"),
                playable = false,
                mythology = Mythology.CELTIC,
                hitPoints = 4200,
                rating = 9.0
            ),
            MythicalCharacter(
                id = 14,
                image = "https://static.wikia.nocookie.net/smite_gamepedia/images/c/c9/SkinArt_Freya_Default_V2.jpg/revision/latest?cb=20160713210036",
                name = "Freya",
                release = LocalDate.parse("2022-05-18"),
                playable = true,
                mythology = Mythology.NORSE,
                hitPoints = 3000,
                rating = 8.4
            ),
            MythicalCharacter(
                id = 15,
                image = "https://static.wikia.nocookie.net/smite/images/c/c6/Chaac.jpg/revision/latest?cb=20150425234750",
                name = "Chaac",
                release = LocalDate.parse("2022-06-29"),
                playable = true,
                mythology = Mythology.MAYAN,
                hitPoints = 3300,
                rating = 7.6
            )
        )
    }

    fun loadAbilities() : List<Ability>{
        return listOf<Ability>(
            Ability(
                id = 1,
                mythicalCharacterId = 1,
                name = "Shields of Olympus",
                power = 400,
                type = AbilityType.MAGIC
            ),
            Ability(
                id = 2,
                mythicalCharacterId = 2,
                name = "Back stab",
                power = 850,
                type = AbilityType.PHYSICAL
            ),
            Ability(
                id = 3,
                mythicalCharacterId = 3,
                name = "Strength of Hercules",
                power = 950,
                type = AbilityType.PHYSICAL
            ),
            Ability(
                id = 4,
                mythicalCharacterId = 4,
                name = "Curse of the Dead",
                power = 800,
                type = AbilityType.MAGIC
            ),
            Ability(
                id = 5,
                mythicalCharacterId = 6,
                name = "Launching Rainbow",
                power = 1000,
                type = AbilityType.MAGIC
            ),
            Ability(
                id = 6,
                mythicalCharacterId = 6,
                name = "Moonlight Beam",
                power = 750,
                type = AbilityType.MAGIC
            ),
            Ability(
                id = 7,
                mythicalCharacterId = 7,
                name = "Feather Step",
                power = 700,
                type = AbilityType.PHYSICAL
            ),
            Ability(
                id = 8,
                mythicalCharacterId = 11,
                name = "Suppress Arrows",
                power = 850,
                type = AbilityType.ARROW
            ),
            Ability(
                id = 9,
                mythicalCharacterId = 13,
                name = "Begin the Hunt",
                power = 600,
                type = AbilityType.ARROW
            ),
            Ability(
                id = 10,
                mythicalCharacterId = 15,
                name = "Thunder and Lightning",
                power = 650,
                type = AbilityType.PHYSICAL
            ),
            Ability(
                id = 11,
                mythicalCharacterId = 15,
                name = "Rain Call",
                power = 0,
                type = AbilityType.HEALING
            )
        )
    }
    
     */
}