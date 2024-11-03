package com.example.riseup.util

import com.example.riseup.data.local.entities.CharacterEntity
import com.example.riseup.model.character.Character

object CharacterMapper {

    fun fromEntity(entity: CharacterEntity): Character {
        return Character(
            id = entity.id,
            level = entity.level,
            currentXp = entity.currentXp,
            xpForNextLevel = entity.xpForNextLevel
        )
    }

    fun toEntity(model: Character): CharacterEntity {
        return CharacterEntity(
            id = model.id,
            level = model.level,
            currentXp = model.currentXp,
            xpForNextLevel = model.xpForNextLevel
        )
    }
}